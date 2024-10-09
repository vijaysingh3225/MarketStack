import React from 'react';
import HomeNav from '../Components/HomeNav';
import HomePageCards from '../Components/HomePageCards';
import Footer from '../Components/Footer';
import '../StyleSheets/Home.css';
import { NavLink} from 'react-router-dom';

const Home: React.FC = () => {

  return (
    <div>
        <HomeNav/>
        <div className='head-container'>
          <span className='header-text'>Stack Your Knowledge, Elevate Your Trading</span>
          <NavLink to="/login" style={{ margin: 'auto' }}>
            <button className='start-button'>Start Now</button>
          </NavLink>
          <img src="/images/DashBoard.png" alt="" className='dashboard-sample'/>
        </div>
        <HomePageCards/>
        <Footer/>
    </div>
  );
};

export default Home;