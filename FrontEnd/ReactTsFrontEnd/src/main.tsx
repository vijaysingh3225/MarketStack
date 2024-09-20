import { createRoot } from 'react-dom/client';
import './index.css'; // Global styles
import App from './App';
import { StrictMode } from 'react';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <App />
  </StrictMode>
);
