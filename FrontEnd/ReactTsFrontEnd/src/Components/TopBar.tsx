import React, { useState } from 'react';
import '../StyleSheets/TopBar.css';

interface TopBarProps {
  onTradeCountChange: (count: number) => void;
}

const TopBar: React.FC<TopBarProps> = ({ onTradeCountChange }) => {
  const [customCount, setCustomCount] = useState<number | ''>(''); // State to store the input value

  // Function to handle input change and update the state
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setCustomCount(value === '' ? '' : parseInt(value, 10)); // Convert input value to number if not empty
  };

  // Function to handle submit button click
  const handleSubmit = () => {
    if (typeof customCount === 'number' && customCount > 0) {
      onTradeCountChange(customCount); // Only update if the input is a valid number
    }
  };

  return (
    <div className='top-bar'>
      <button className='timeFrameSelection' onClick={() => onTradeCountChange(0)}>All Trades</button>
      <button className='timeFrameSelection' onClick={() => onTradeCountChange(100)}>100 Trades</button>
      <button className='timeFrameSelection' onClick={() => onTradeCountChange(50)}>50 Trades</button>
      <button className='timeFrameSelection' onClick={() => onTradeCountChange(25)}>25 Trades</button>
      <input 
        type="text" 
        className='custom-input' 
        value={customCount === '' ? '' : customCount} // Display the input value
        onChange={handleInputChange} 
        placeholder="Enter trade count" 
      />
      <button className='timeFrameSelection' onClick={handleSubmit}>Submit</button>
    </div>
  );
};

export default TopBar;