import React, { useEffect, useState } from "react";
import axios from "axios";
import "./StyleSheets/TradeList.css";

interface TradeExec {
  symbol: string;
  tradeDate: string;
  price: number;
}

interface Trade {
  id: string;
  tradeExecs: TradeExec[];
  profitLoss: number;
  avgEntry: number;
  avgExit: number;
  shortLong: boolean;
  maxPosition: number;
}

const TradeList: React.FC = () => {
  const [trades, setTrades] = useState<Trade[]>([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/v1/closedTrades")
      .then((response) => {
      
        const sortedTrades = response.data.sort(
          (a: Trade, b: Trade) =>
            new Date(b.tradeExecs[0].tradeDate).getTime() -
            new Date(a.tradeExecs[0].tradeDate).getTime()
        );

        const latestTrades = sortedTrades.slice(0, 20);

        setTrades(latestTrades);
      })
      .catch((error) => {
        console.error("There was an error fetching the trades!", error);
      });
  }, []);


  const getProfitLossColor = (profitLoss: number) => {
    return profitLoss >= 0 ? "#7A9163" : "#AC3231";
  };

  const renderTradeItem = (trade: Trade) => (
    <tr key={trade.id}>
      <td>{trade.tradeExecs[0].tradeDate}</td>
      <td>{trade.tradeExecs[0].symbol}</td>
      <td className='profitLoss' style={{ color: getProfitLossColor(trade.profitLoss) }}>
        ${trade.profitLoss.toFixed(2)}
      </td>
      <td>{trade.shortLong ? "Long" : "Short"}</td>
      <td>{trade.avgEntry.toFixed(2)}</td>
      <td>{trade.avgExit.toFixed(2)}</td>
      <td>{trade.maxPosition}</td>
    </tr>
  );

  return (
    <div className="tradeList">
      <table>
        <thead>
          <tr>
            <th>Date</th>
            <th>Symbol</th>
            <th>Profit/Loss</th>
            <th>Direction</th>
            <th>Avg Entry</th>
            <th>Avg Exit</th>
            <th>Max Position</th>
          </tr>
        </thead>
        <tbody>{trades.map(renderTradeItem)}</tbody>
      </table>
    </div>
  );
};

export default TradeList;