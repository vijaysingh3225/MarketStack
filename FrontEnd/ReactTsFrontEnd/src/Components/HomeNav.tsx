import React from 'react';
import '../StyleSheets/HomeNav.css';
import { NavLink} from 'react-router-dom';

const HomeNav: React.FC = () => {
  return (
    <div className='nav'>
        <div className='title'>
          <NavLink to="/">
          <img src="/images/TitleLogo.png" alt="" className='titleLogo' />
          </NavLink>
      </div>
      <div className='nav-buttons'>
        <NavLink to="/" className='nav-item'><div>Home</div></NavLink>
        <NavLink to="/login" className='nav-item'><div>Features</div></NavLink>
        <NavLink to="/login" className='nav-item'><div>Sign In</div></NavLink>
        <NavLink to="/dashboard" className='nav-item'><div>Dashboard*BETA*</div></NavLink>
        
      </div>
    </div>
  );
};

export default HomeNav;