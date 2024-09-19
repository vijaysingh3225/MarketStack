import NavBar from './Components/NavBar.tsx'
import { createRoot } from 'react-dom/client'
import './index.css'
import TradeList from './Components/TradeList.tsx'
import { StrictMode } from 'react'
import PnlGraph from './Components/PnlGraph.tsx'
import PnlHistogram from './Components/PnlHistogram.tsx'
import ImportButton from './Components/import.tsx'
import TopBar from './Components/TopBar.tsx'


createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <div className='mainContent'>
    <TopBar/>
    <PnlHistogram />
    <PnlGraph/>
    <TradeList />
    <ImportButton/>
    </div>
    <NavBar />

    
  </StrictMode>
)
