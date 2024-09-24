import { createRoot } from 'react-dom/client';
import './StyleSheets/index.css'; 
import App from './App';
import { StrictMode } from 'react';
import { BrowserRouter } from 'react-router-dom';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </StrictMode>
);
