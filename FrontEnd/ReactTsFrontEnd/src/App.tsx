import React from 'react';
import { useEffect, useState } from 'react';
import NavBar from './Components/NavBar';
import TradeList from './Components/TradeList';
import PnlGraph from './Components/PnlGraph';
import PnlHistogram from './Components/PnlHistogram';
import ImportButton from './Components/import';
import TopBar from './Components/TopBar';
import DashStats from './Components/DashStats';
import './index.css';

const App: React.FC = () => {
  const [tradeCount, setTradeCount] = useState<number>(50);

  const handleTradeCountChange = (count: number) => {
    setTradeCount(count);
  };
  
  return (
    <div className='mainContent'>
      <TopBar onTradeCountChange={handleTradeCountChange} />
      <PnlHistogram tradeCount={tradeCount} />
      <PnlGraph tradeCount={tradeCount} />
      <TradeList />
      <DashStats />
      <NavBar />
    </div>
  );
};

export default App;