import React from 'react';
import { NavLink, useLocation } from 'react-router-dom';
import '../StyleSheets/NavBar.css';

const NavBar: React.FC = () => {
  const location = useLocation(); 

  return (
    <div className='navbar'>
      <div className='title'>
        <img src="src/images/TitleLogo.png" alt="" className='titleLogo' />
      </div>
      <div className='navContainer'>
        <NavLink to="/" className={`navItem ${location.pathname === '/' ? 'selected' : ''}`}>
          <img src="src/images/dashboard-white.png" alt="Dashboard Logo" className='logo' />
          <span>Dashboard</span>
        </NavLink>
        <NavLink to="/graphs" className={`navItem ${location.pathname === '/graphs' ? 'selected' : ''}`}>
          <img src="src/images/graphLogo-white.png" alt="Graphs Logo" className='logo' />
          <span>Graphs</span>
        </NavLink>
        <NavLink to="/statistics" className={`navItem ${location.pathname === '/statistics' ? 'selected' : ''}`}>
          <img src="src/images/statisticLogo.png" alt="Statistics Logo" className='logo' />
          <span>Statistics</span>
        </NavLink>
        <NavLink to="/calendar" className={`navItem ${location.pathname === '/calendar' ? 'selected' : ''}`}>
          <img src="src/images/calanderIcon.png" alt="Calendar Logo" className='logo' />
          <span>Calendar</span>
        </NavLink>
        <NavLink to="/tools" className={`navItem ${location.pathname === '/tools' ? 'selected' : ''}`}>
          <img src="src/images/toolsIcon.png" alt="Tools Logo" className='logo' />
          <span>Tools</span>
        </NavLink>
      </div>
      <div className='navFooter'>
      <NavLink to="/import" className={`footerItem ${location.pathname === '/import' ? 'footerItemSelected' : ''}`}>
          <img src="src/images/importIcon.png" alt="Import Icon" className='logo' />
          <span>Import</span>
          </NavLink>
          <NavLink to="/account" className={`footerItem ${location.pathname === '/account' ? 'footerItemSelected' : ''}`}>
          <img src="src/images/accountIcon.png" alt="Account Icon" className='logo' />
          <span>Account</span>
          </NavLink>
      </div>
    </div>
  );
};

export default NavBar;
