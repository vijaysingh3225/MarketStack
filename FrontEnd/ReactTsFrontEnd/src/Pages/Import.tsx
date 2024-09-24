import React from 'react';
import { useEffect, useState } from 'react';
import NavBar from '../Components/NavBar';
import '../StyleSheets/index.css';
import ImportButton from '../Components/ImportComponent';

const Import: React.FC = () => {


  return (
    <div className='mainContent'>
      <NavBar />
      <ImportButton/>
    </div>  
  );
};

export default Import;