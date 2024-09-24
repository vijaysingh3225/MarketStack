import React, { useEffect, useState } from "react";
import { Doughnut } from "react-chartjs-2";
import axios from "axios";
import "../StyleSheets/DashStats.css";
import { Chart as ChartJS, ArcElement, Tooltip, Legend, ChartOptions } from "chart.js";

ChartJS.register(ArcElement, Tooltip, Legend);

interface DashStatsProps {
  tradeCount: number; 
}

const DashStats: React.FC<DashStatsProps> = ({ tradeCount }) => {
  const [winRateData, setWinRateData] = useState<any>(null);
  const [winRate, setWinRate] = useState<number | null>(null);

  useEffect(() => {
    const fetchTrades = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/v1/closedTrades");
        const trades = response.data;

        // Sort trades by date to ensure chronological order
        const sortedTrades = trades.sort(
          (a: any, b: any) =>
            new Date(a.tradeExecs[0].tradeDate).getTime() - new Date(b.tradeExecs[0].tradeDate).getTime()
        );

        const tradesToShow = tradeCount === 0 ? sortedTrades : sortedTrades.slice(-tradeCount);

        const totalTrades = tradesToShow.length;

        console.log("Total trades:", totalTrades);

        const winningTrades = tradesToShow.filter((trade: any) => Number(trade.profitLoss) > 0).length;

        console.log("Winning trades:", winningTrades);

        const calculatedWinRate = totalTrades > 0 ? (winningTrades / totalTrades) * 100 : 0;

        console.log("Calculated Win Rate:", calculatedWinRate);

        setWinRateData({
          labels: ["Wins", "Losses"],
          datasets: [
            {
              data: [winningTrades, totalTrades - winningTrades],
              backgroundColor: ["#7A9163", "#AC3231"],
              hoverBackgroundColor: ["#6B9A55", "#A61F20"],
              borderWidth: 0,
            },
          ],
        });

        setWinRate(calculatedWinRate);
      } catch (error) {
        console.error("There was an error fetching the trades!", error);
      }
    };

    fetchTrades();
  }, [tradeCount]); 

  const options: ChartOptions<'doughnut'> = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        position: 'top',
        labels: {
          color: 'white',
        },
      },
      tooltip: {
        callbacks: {
          label: (tooltipItem) => {
            const label = tooltipItem.label;
            const value = tooltipItem.raw;
            return `${label}: ${value}`;
          },
        },
      },
    },
    cutout: '80%',
  };

  return (
    <div className='container'>
      <div className="performance-container">
        <div className='performance-item'>
          {winRateData ? (
            <>
              <Doughnut data={winRateData} options={options} />
              <span className='winrate'>{winRate !== null ? `${winRate.toFixed(2)}%` : "Calculating win rate..."}</span>
            </>
          ) : (
            <p>Loading win rate...</p>
          )}
        </div>
        <div className="stats performance-item">
          <div className="row-container">
          <span className="item">Average Win</span>
          <span className="item">---</span>
          </div>
        </div>
      </div>
      <div className='performance-container'></div>
    </div>
  );
};

export default DashStats;