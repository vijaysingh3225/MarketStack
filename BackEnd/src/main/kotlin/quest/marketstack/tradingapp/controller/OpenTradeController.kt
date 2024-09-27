@file:Suppress("ktlint:standard:no-wildcard-imports")

package quest.marketstack.tradingapp.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import quest.marketstack.tradingapp.model.OpenTrade
import quest.marketstack.tradingapp.model.TradeExec
import quest.marketstack.tradingapp.service.opentrades.OpenTradeServiceInterface

@RestController
@RequestMapping("\${api.open-trade.base-url}")
@CrossOrigin(origins = ["*"])
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
