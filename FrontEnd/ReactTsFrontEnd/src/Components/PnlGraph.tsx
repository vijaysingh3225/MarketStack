import React, { useEffect, useState } from 'react';
import { Line } from 'react-chartjs-2';
import axios from 'axios';
import '../StyleSheets/PnlGraph.css';
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend, ChartOptions } from 'chart.js';

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

interface PnlGraphProps {
  tradeCount: number; 
}

const PnlGraph: React.FC<PnlGraphProps> = ({ tradeCount }) => {
  const [chartData, setChartData] = useState<any>(null);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/closedTrades")
      .then((response) => {
        const sortedTrades = response.data.sort(
          (a: Trade, b: Trade) =>
            new Date(a.tradeExecs[0].tradeDate).getTime() - new Date(b.tradeExecs[0].tradeDate).getTime()
        );

        const tradesToShow = tradeCount === 0 ? sortedTrades : sortedTrades.slice(-tradeCount);

        let cumulativePnL = 0;
        const cumulativeData = tradesToShow.map((trade: Trade) => {
          cumulativePnL += trade.profitLoss;
          return {
            tradeDate: trade.tradeExecs[0].tradeDate,
            cumulativePnL: cumulativePnL,
          };
        });

        setChartData({
          labels: cumulativeData.map((data: any) => new Date(data.tradeDate).toLocaleDateString()),
          datasets: [
            {
              label: 'Cumulative Profit/Loss',
              data: cumulativeData.map((data: any) => data.cumulativePnL),
              borderColor: '#7A9163',
              backgroundColor: 'rgb(122, 145, 99, 0.4)',
              fill: true,
              tension: 0.3,
            },
          ],
        });
      })
      .catch((error) => {
        console.error("There was an error fetching the trades!", error);
      });
  }, [tradeCount]); // Re-run the effect when tradeCount changes

  const options: ChartOptions<'line'> = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        position: 'top',
        labels: {
          color: 'white',
        },
      },
      title: {
        display: true,
        text: 'Cumulative Profit/Loss',
        color: 'white',
        font: {
          size: 20,
        },
      },
    },
    scales: {
      x: {
        title: {
          display: true,
          text: 'Date',
          color: 'white',
          font: {
            size: 16,
          },
        },
        ticks: {
          color: 'white',
        },
        grid: {
          color: '#353535',
        },
      },
      y: {
        title: {
          display: true,
          text: 'Cumulative PnL ($)',
          color: 'white',
          font: {
            size: 16,
          },
        },
        ticks: {
          color: 'white',
        },
        grid: {
          color: '#353535',
        },
      },
    },
  };

  return (
    <div className="graph">
      {chartData ? (
        <Line data={chartData} options={options} />
      ) : (
        <p>Loading graph...</p>
      )}
    </div>
  );
};

export default PnlGraph;