import './App.css';
import api from './api/axiosConfig';
import { useState, useEffect } from 'react';

function App() {
  const [tradeExecs, setTradeExecs] = useState([]);

  const getTradeExecs = async () => {
    try {
      const response = await api.get('/api/tradeExecs');
      console.log('Received response:', response.data);
      setTradeExecs(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    getTradeExecs();
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        MarketStack
      </header>
      <ul>
        {tradeExecs.map((tradeExec, index) => (
          <li key={index}>
            {/* Customize how you display each tradeExec */}
            {JSON.stringify(tradeExec)}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;