import React from 'react';
import "./StyleSheets/NavBar.css"

const NavBar: React.FC = () => {
  return <div className='navbar'>
    <div className='title'>
      <img src="src\images\TitleLogo.png" alt="" className='titleLogo'/>
    </div>
    <div className='navContainer'>
        <div className='navItem'>
          <img src="src\images\dashboard-white.png" alt="" className='logo'/>
          <span>Dashboard</span></div>
        <div className='navItem'>
          <img src="src\images\graphLogo-white.png" alt="" className='logo'/>
          <span>Graphs</span>
        </div>
        <div className='navItem'>
          <img src="src\images\statisticLogo.png" alt="" className='logo'/>
          <span>Statistics</span>
        </div>
        <div className='navItem'>
          <img src="src\images\calanderIcon.png" alt="" className='logo'/>
          <span>Calendar</span>
        </div>
        <div className='navItem'>
          <img src="src\images\toolsIcon.png" alt="" className='logo'/>
          <span>Tools</span>
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