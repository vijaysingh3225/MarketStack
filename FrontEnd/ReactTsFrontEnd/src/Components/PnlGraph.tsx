import React, { useEffect, useState } from 'react';
import { Line } from 'react-chartjs-2';
import axios from 'axios';
import "./StyleSheets/PnlGraph.css";
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend, ChartOptions } from 'chart.js';

// Register Chart.js components
ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);

interface TradeExec {
  symbol: string;
  tradeDate: string;
  price: number;
}

interface Trade {
  id: string;
  tradeExecs: TradeExec[];
  profitLoss: number;
}

const PnlGraph: React.FC = () => {
  const [chartData, setChartData] = useState<any>(null);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/closedTrades")
      .then((response) => {
        const sortedTrades = response.data.sort(
          (a: Trade, b: Trade) =>
            new Date(a.tradeExecs[0].tradeDate).getTime() - new Date(b.tradeExecs[0].tradeDate).getTime()
        );

        // Limit to the last 60 trades
        const last60Trades = sortedTrades.slice(-50);

        // Calculate cumulative PnL
        let cumulativePnL = 0;
        const cumulativeData = last60Trades.map((trade: Trade) => {
          cumulativePnL += trade.profitLoss;
          return {
            tradeDate: trade.tradeExecs[0].tradeDate,
            cumulativePnL: cumulativePnL,
          };
        });

        // Set chart data for Chart.js
        setChartData({
          labels: cumulativeData.map((data: any) => new Date(data.tradeDate).toLocaleDateString()),
          datasets: [
            {
              label: 'Cumulative Profit/Loss',
              data: cumulativeData.map((data: any) => data.cumulativePnL),
              borderColor: '#7A9163', // Line color
              backgroundColor: 'rgb(122, 145, 99, 0.4)', // Fill color under the line
              fill: true, // Fill the area under the line
              tension: 0.3, // Smooth curves
            },
          ],
        });
      })
      .catch((error) => {
        console.error("There was an error fetching the trades!", error);
      });
  }, []);

  const options: ChartOptions<'line'> = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        position: 'top',
        labels: {
          color: 'white', // Legend text color
        },
      },
      title: {
        display: true,
        text: 'Cumulative Profit/Loss',
        color: 'white', // Title color
        font: {
          size: 20, // Title font size
        },
      },
    },
    scales: {
      x: {
        title: {
          display: true,
          text: 'Date',
          color: 'white', // X-axis label color
          font: {
            size: 16, // X-axis label font size
          },
        },
        ticks: {
          color: 'white', // X-axis tick labels color
        },
        grid: {
          color: '#353535', // X-axis grid lines color
        },
      },
      y: {
        title: {
          display: true,
          text: 'Cumulative PnL ($)',
          color: 'white', // Y-axis label color
          font: {
            size: 16, // Y-axis label font size
          },
        },
        ticks: {
          color: 'white', // Y-axis tick labels color
        },
        grid: {
          color: '#353535', // Y-axis grid lines color
        },
      },
    },
  };

  return (
    <div className="graph">
      {chartData ? (
        <Line
          data={chartData}
          options={options}
        />
      ) : (
        <p>Loading graph...</p>
      )}
    </div>
  );
};

export default PnlGraph;