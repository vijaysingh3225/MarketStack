package quest.marketstack.TradingApp.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import quest.marketstack.TradingApp.model.ClosedTrade
import quest.marketstack.TradingApp.model.OpenTrade
import quest.marketstack.TradingApp.model.TradeExec
import quest.marketstack.TradingApp.service.ClosedTrades.ClosedTradeServiceInterface

@RestController
@RequestMapping("/api/closedTrades")
@CrossOrigin(origins = ["http://localhost:3000","http://192.168.86.244:3000"])
class ClosedTradeController(private val service: ClosedTradeServiceInterface) {
    @GetMapping
    fun getAllTrades(): Collection<ClosedTrade> = service.getTrades()

}