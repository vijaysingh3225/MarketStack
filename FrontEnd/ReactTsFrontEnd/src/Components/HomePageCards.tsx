import React from 'react';
import '../StyleSheets/HomePageCards.css';


const HomePageCards: React.FC = () => {
  return (
    <div className='card-container fade-in'>
        <div className='card'>
          <img src="/images/importIconCard.png" alt="Import Icon" className='card-icon' />
            <span className='card-title'>Import</span>
            <p className='card-body'>Import trade data provided by your broker of choice, have the data sorted and displayed on your dashboard</p>
        </div>
        <div className='card'>
            <span className='card-title'>Analyze</span>
        </div>
        <div className='card'>
            <span className='card-title'>Improve</span>
        </div>
    </div>
  );
};

export default HomePageCards;