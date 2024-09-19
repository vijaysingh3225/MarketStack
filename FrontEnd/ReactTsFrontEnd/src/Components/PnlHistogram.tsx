import React, { useEffect, useState } from 'react';
import { Bar } from 'react-chartjs-2';
import axios from 'axios';
import "./StyleSheets/PnlHistogram.css";
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, ChartOptions } from 'chart.js';

// Register Chart.js components
ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
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

const PnlHistogram: React.FC = () => {
  const [chartData, setChartData] = useState<{
    labels: string[];
    datasets: {
      label: string;
      data: number[];
      backgroundColor: string[];
      borderColor: string[];
      borderWidth: number;
    }[];
  } | null>(null);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/closedTrades")
      .then((response) => {
        // Prepare data for histogram, limited to the last 60 trades
        const sortedTrades = response.data.sort(
          (a: Trade, b: Trade) =>
            new Date(a.tradeExecs[0].tradeDate).getTime() - new Date(b.tradeExecs[0].tradeDate).getTime()
        );

        // Limit to the last 60 trades
        const last60Trades = sortedTrades.slice(-50);

        // Extract trade dates and values
        const tradeDates: string[] = last60Trades.map((trade: Trade) => new Date(trade.tradeExecs[0].tradeDate).toLocaleDateString());
        const tradeValues: number[] = last60Trades.map((trade: Trade) => trade.profitLoss);

        // Set bar colors based on value
        const barColors: string[] = tradeValues.map(value =>
          value >= 0 ? '#7A9163' : '#AC3231'
        );

        // Set chart data for Chart.js
        setChartData({
          labels: tradeDates,
          datasets: [
            {
              label: 'Trade Profit/Loss',
              data: tradeValues,
              backgroundColor: barColors, // Dynamic bar colors
              borderColor: barColors.map(color => color.replace('0.5', '1')), // Adjust border color
              borderWidth: 1,
            },
          ],
        });
      })
      .catch((error) => {
        console.error("There was an error fetching the trades!", error);
      });
  }, []);

  const options: ChartOptions<'bar'> = {
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
        text: 'Trade Histogram',
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
          text: 'Trade Date',
          color: 'white', // X-axis label color
          font: {
            size: 16, // X-axis label font size
          },
        },
        ticks: {
          color: 'white', // X-axis tick labels color
          maxRotation: 90, // Rotate labels for better readability if needed
          autoSkip: true, // Auto-skip labels to avoid clutter
        },
        grid: {
          color: '#353535', // X-axis grid lines color
        },
      },
      y: {
        title: {
          display: true,
          text: 'Profit/Loss ($)',
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
        <Bar
          data={chartData}
          options={options}
        />
      ) : (
        <p>Loading histogram...</p>
      )}
    </div>
  );
};

export default PnlHistogram;