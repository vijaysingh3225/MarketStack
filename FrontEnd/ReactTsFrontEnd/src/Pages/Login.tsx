import React from 'react';
import '../StyleSheets/Login.css';
import LoginForm from '../Components/LoginForm';
import HomeNav from '../Components/HomeNav';

const Import: React.FC = () => {


  return (
    <div>
        <HomeNav/>
        <LoginForm/>
    </div>
  );
};

export default Import;