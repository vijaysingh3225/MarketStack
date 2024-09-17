import React from 'react';
import "./StyleSheets/NavBar.css"

const NavBar: React.FC = () => {
  return <div className='navbar'>
    <div className='title'></div>
    <div className='navContainer'>
        <div className='navItem'></div>
        <div className='navItem'></div>
        <div className='navItem'></div>
        <div className='navItem'></div>
        <div className='navItem'></div>
    </div>
  </div>;
};

export default NavBar;