import React from 'react';
import '../StyleSheets/Login.css';
import LoginForm from '../Components/LoginForm';

const Import: React.FC = () => {


  return (
    <div className='login-form-container'>
        <LoginForm />
    </div>  
  );
};

export default Import;