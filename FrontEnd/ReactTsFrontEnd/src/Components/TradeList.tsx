import React, { useEffect, useState } from 'react';
import axios from 'axios';

interface TradeExec {
  symbol: string;
  tradeDate: string;
}

interface Trade {
  id: string;
  tradeExecs: TradeExec[];
  profitLoss: number;
}

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
      <h3>Symbol: {trade.tradeExecs[0].symbol}</h3>
      <p>Date: {trade.tradeExecs[0].tradeDate}</p>
      <p>Profit/Loss: ${trade.profitLoss}</p>

    </div>
  );

  return (
    <div>
      {trades.map(renderTradeItem)}
    </div>
  );
};

export default TradeList;