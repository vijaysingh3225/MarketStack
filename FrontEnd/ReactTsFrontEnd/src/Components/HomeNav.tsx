import React from 'react';
import '../StyleSheets/HomeNav.css';
import { NavLink, useLocation } from 'react-router-dom';

const HomeNav: React.FC = () => {
  return (
    <div className='nav'>
        <div className='title'>
        <img src="src/images/TitleLogo.png" alt="" className='titleLogo' />
      </div>
      <div className='nav-buttons'>
        <NavLink to="/" className='nav-item'><div>Home</div></NavLink>
        <NavLink to="/Login" className='nav-item'><div>Features</div></NavLink>
        <NavLink to="/Login" className='nav-item'><div>Sign In</div></NavLink>
        <NavLink to="/Login" className='nav-item'><div>Register</div></NavLink>
        
      </div>
    </div>
  );
};

export default HomeNav;