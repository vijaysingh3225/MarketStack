@file:Suppress("ktlint:standard:no-wildcard-imports")

package quest.marketstack.tradingapp.controller

import org.springframework.web.bind.annotation.*
import quest.marketstack.tradingapp.model.ClosedTrade
import quest.marketstack.tradingapp.service.closedtrades.ClosedTradeServiceInterface

@RestController
@RequestMapping("\${api.closed-trade.base-url}")
@CrossOrigin(origins = ["http://localhost:5173", "http://192.168.86.244:3000"])
class ClosedTradeController(
    private val service: ClosedTradeServiceInterface,
) {
    @GetMapping
    fun getAllTrades(): Collection<ClosedTrade> = service.getTrades()
}
