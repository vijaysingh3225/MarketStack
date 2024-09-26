import React from 'react';
import HomeNav from '../Components/HomeNav';
import '../StyleSheets/Home.css';
import { NavLink} from 'react-router-dom';

const Home: React.FC = () => {

  return (
    <div>
        <HomeNav/>
        <div className='head-container'>
          <span className='header-text'>Stack Your Knowledge, Elevate Your Trading</span>
          <NavLink to="/Login" style={{ margin: 'auto' }}>
            <button className='start-button'>Start Now</button>
          </NavLink>
          <img src="src\images\DashBoard.png" alt="" className='dashboard-sample'/>
        </div>
    </div>
  );
};

export default Home;