package quest.marketstack.TradingApp.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import quest.marketstack.TradingApp.model.Trade
import quest.marketstack.TradingApp.service.ClosedTrades.ClosedTradeServiceInterface

@RestController
@RequestMapping("/api/closedTrades")
    class ClosedTradeController(private val service: ClosedTradeServiceInterface){

        @GetMapping
        fun getAllTrades(): Collection<Trade> = service.getTrades()
}
