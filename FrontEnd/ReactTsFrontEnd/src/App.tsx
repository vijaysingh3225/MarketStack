import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Account from './Pages/Account';
import Dashboard from './Pages/Dashboard';
import Calendar from './Pages/Calendar';
import Import from './Pages/Import';
import Statistics from './Pages/Statistics';
import Tools from './Pages/Tools';
import Home from './Pages/Home';
import Login from './Pages/Login';


const App: React.FC = () => {

  return (
    <div>
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/dashboard" element={<Dashboard />} />
      <Route path="/account" element={<Account />} />
      <Route path="/calendar" element={<Calendar />} />
      <Route path="/graphs" element={<Calendar />} />
      <Route path="/import" element={<Import />} />
      <Route path="/statistics" element={<Statistics />} />
      <Route path="/tools" element={<Tools />} />
      <Route path="/login" element={<Login />} />
    </Routes>
  </div>
  );
};

export default App;