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
     
        const sortedTrades = response.data.sort(
          (a: Trade, b: Trade) =>
            new Date(a.tradeExecs[0].tradeDate).getTime() - new Date(b.tradeExecs[0].tradeDate).getTime()
        );

      
        const last60Trades = sortedTrades.slice(-50);

      
        const tradeDates: string[] = last60Trades.map((trade: Trade) => new Date(trade.tradeExecs[0].tradeDate).toLocaleDateString());
        const tradeValues: number[] = last60Trades.map((trade: Trade) => trade.profitLoss);

       
        const barColors: string[] = tradeValues.map(value =>
          value >= 0 ? '#7A9163' : '#AC3231'
        );

       
        setChartData({
          labels: tradeDates,
          datasets: [
            {
              label: 'Trade Profit/Loss',
              data: tradeValues,
              backgroundColor: barColors, 
              borderColor: barColors.map(color => color.replace('0.5', '1')), 
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
          color: 'white',
        },
      },
      title: {
        display: true,
        text: 'Trade Histogram',
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
          text: 'Trade Date',
          color: 'white',
          font: {
            size: 16, 
          },
        },
        ticks: {
          color: 'white', 
          maxRotation: 90, 
          autoSkip: true, 
        },
        grid: {
          color: '#353535', 
        },
      },
      y: {
        title: {
          display: true,
          text: 'Profit/Loss ($)',
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