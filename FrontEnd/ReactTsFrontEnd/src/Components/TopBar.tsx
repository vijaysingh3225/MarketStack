import React from 'react';
import '../StyleSheets/TopBar.css';

interface TopBarProps {
  onTradeCountChange: (count: number) => void;
}

const TopBar: React.FC<TopBarProps> = ({ onTradeCountChange }) => {
  return (
    <div className='top-bar'>
      <button className='timeFrameSelection' onClick={() => onTradeCountChange(0)}>All Trades</button>
      <button className='timeFrameSelection' onClick={() => onTradeCountChange(100)}>100 Trades</button>
      <button className='timeFrameSelection' onClick={() => onTradeCountChange(50)}>50 Trades</button>
      <button className='timeFrameSelection' onClick={() => onTradeCountChange(25)}>25 Trades</button>
    </div>
  );
};

export default TopBar;