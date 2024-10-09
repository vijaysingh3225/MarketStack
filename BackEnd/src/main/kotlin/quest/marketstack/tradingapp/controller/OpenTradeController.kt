@file:Suppress("ktlint:standard:no-wildcard-imports")

package quest.marketstack.tradingapp.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import quest.marketstack.tradingapp.model.OpenTrade
import quest.marketstack.tradingapp.model.TradeExec
import quest.marketstack.tradingapp.service.opentrades.OpenTradeServiceInterface

@RestController
@CrossOrigin(origins = ["https://marketstack.quest", "http://localhost:5173/"])
@RequestMapping("\${api.open-trade.base-url}")
class OpenTradeController(
    private val service: OpenTradeServiceInterface,
) {
    @GetMapping
    fun getTradeExecs(): Collection<OpenTrade> = service.getTrades()

    @GetMapping("/{id}")
    fun getTradeExec(
        @PathVariable id: String,
    ) = service.getTrade(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addTradeExec(
        @RequestBody execList: Collection<TradeExec>,
    ): Collection<TradeExec> = service.addTradeExecs(execList)
}
