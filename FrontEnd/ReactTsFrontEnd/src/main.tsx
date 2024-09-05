import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './Components/App.tsx'
import ImportButton from './Components/import.tsx'
import './index.css'
import TradeList from './Components/TradeList.tsx'


createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <ImportButton />
    <TradeList />
  </StrictMode>,
)
