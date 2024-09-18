import React from 'react';
import "./StyleSheets/NavBar.css"

const NavBar: React.FC = () => {
  return <div className='navbar'>
    <div className='title'></div>
    <div className='navContainer'>
        <div className='navItem'>
          <img src="src\images\dashboard-white.png" alt="" className='logo'/>
          <span>Dashboard</span></div>
        <div className='navItem'>
          <img src="src\images\graphLogo-white.png" alt="" className='logo'/>
          <span>Graphs</span>
        </div>
        <div className='navItem'>
          <span>Statistics</span>
        </div>
        <div className='navItem'>
          <span>Calendar</span>
        </div>
        <div className='navItem'>
          <span>Visualizer</span>
        </div>
    </div>
    <div className='navFooter'>
      <div className='footerItem'>
        <img src="src\images\importIcon.png" alt="" className='logo'/>
        <span>Import</span>
        </div>
      <div className='footerItem spacer'>
      <img src="src\images\accountIcon.png" alt="" className='logo'/>
        <span>Account</span>
        </div>
    </div>
  </div>;
};

export default NavBar;