import NavBar from './Components/NavBar.tsx'
import { createRoot } from 'react-dom/client'
import './index.css'
import TradeList from './Components/TradeList.tsx'
import { StrictMode } from 'react'
import PnlGraph from './Components/PnlGraph.tsx'


createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <div className='mainContent'>
    <TradeList />
    <PnlGraph/>
    </div>
    <NavBar />
    
  </StrictMode>
)
