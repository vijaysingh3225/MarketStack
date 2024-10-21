import React from 'react';
import '../StyleSheets/HomePageCards.css';


const HomePageCards: React.FC = () => {
  return (
    <div className='card-container fade-in'>
        <div className='card'>
            <img src="/images/importIconCard.png" alt="Import Icon" className='card-icon-import' />
            <span className='card-title'>Import</span>
            <p className='card-body'>Easily upload your trade history CSV file from your broker to get started. Our system processes the data to create a comprehensive journal of all your trades.</p>
        </div>
        <div className='card'>
          <img src="/images/analyzeIcon.png" alt="Import Icon" className='card-icon' />
            <span className='card-title'>Analyze</span>
            <p className='card-body'>Dive into detailed performance metrics and insights from your trade history. Track your wins, losses, and overall trading patterns to better understand your strengths and weaknesses.</p>
        </div>
        <div className='card'>
          <img src="/images/improveIcon.png" alt="Import Icon" className='card-icon' />
            <span className='card-title'>Improve</span>
            <p className='card-body'>Leverage actionable tips and strategies based on your trading data. Fine-tune your approach and make data-driven adjustments to enhance future performance</p>
        </div>
    </div>
  );
};

export default HomePageCards;