@file:Suppress("ktlint:standard:no-wildcard-imports")

package quest.marketstack.tradingapp.controller

import org.springframework.web.bind.annotation.*
import quest.marketstack.tradingapp.model.ClosedTrade
import quest.marketstack.tradingapp.service.closedtrades.ClosedTradeServiceInterface

@RestController
@RequestMapping("\${api.closed-trade.base-url}")
@CrossOrigin(origins = ["*"])
class ClosedTradeController(
    private val service: ClosedTradeServiceInterface,
) {
    @GetMapping
    fun getAllTrades(): Collection<ClosedTrade> = service.getTrades()
}
