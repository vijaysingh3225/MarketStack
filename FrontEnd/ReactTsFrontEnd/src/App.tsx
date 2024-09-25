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
      <Route path="/Dashboard" element={<Dashboard />} />
      <Route path="/Account" element={<Account />} />
      <Route path="/Calendar" element={<Calendar />} />
      <Route path="/Graphs" element={<Calendar />} />
      <Route path="/Import" element={<Import />} />
      <Route path="/Statistics" element={<Statistics />} />
      <Route path="/Tools" element={<Tools />} />
      <Route path="/Login" element={<Login />} />
    </Routes>
  </div>
  );
};

export default App;