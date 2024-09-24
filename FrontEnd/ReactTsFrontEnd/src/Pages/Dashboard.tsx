import React from 'react';
import { useEffect, useState } from 'react';
import NavBar from '../Components/NavBar';
import TradeList from '../Components/TradeList';
import PnlGraph from '../Components/PnlGraph';
import PnlHistogram from '../Components/PnlHistogram';
import TopBar from '../Components/TopBar';
import DashStats from '../Components/DashStats';
import { Routes, Route } from 'react-router-dom';
import Login from './Account';
import '../StyleSheets/index.css';

const Dashboard: React.FC = () => {
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
      <DashStats tradeCount={tradeCount}/>
      <NavBar />
      <Routes>
      <Route path="/login" element={<Login />} />
      </Routes>
    </div>
  );
};

export default Dashboard;