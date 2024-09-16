import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './TradeList.css';

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
// thymeleaf renderer
const TradeList: React.FC = () => {
  const [trades, setTrades] = useState<Trade[]>([]);
  useEffect(() => {
    axios.get('http://localhost:8080/api/v1/closedTrades')
      .then(response => {
        setTrades(response.data);
      })
      .catch(error => {
        console.error("There was an error fetching the trades!", error);
      });
  }, []);

  const renderTradeItem = (trade: Trade) => (
    <div key={trade.id} className="trade-item">
      <table>
        <tr>
          <td>{trade.tradeExecs[0].tradeDate}</td>
          <td>{trade.tradeExecs[0].symbol}</td>
          <td>${trade.profitLoss.toFixed(2)}</td>
        </tr>
      </table>

    </div>
  );

  return (
    <div>
      <table>
        <th>Date</th>
        <th>Symbol</th>
        <th>Profit/Loss</th>
      </table>
      {trades.map(renderTradeItem)}
    </div>
  );
};

export default TradeList;