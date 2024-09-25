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
      <NavLink to="/Login" ><div className='nav-item'>Home</div></NavLink>
        <NavLink to="/Login" ><div className='nav-item'>Features</div></NavLink>
        <NavLink to="/Login" ><div className='nav-item'>Sign In</div></NavLink>
        <NavLink to="/Login" ><div className='nav-item'>Register</div></NavLink>
        
      </div>
    </div>
  );
};

export default HomeNav;