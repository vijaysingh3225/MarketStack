import React from 'react';
import './StyleSheets/TopBar.css'; // Import the CSS file for styling

const TopBar: React.FC = () => {
  return (
    <div className='top-bar'>
      <button className='timeFrameSelection'>All Trades</button>
      <button className='timeFrameSelection'>100 Trades</button>
      <button className='timeFrameSelection'>50 Trades</button>
      <button className='timeFrameSelection'>25 Trades</button>
    </div>
  );
};

export default TopBar;