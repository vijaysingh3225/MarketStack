package quest.marketstack.TradingApp.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import quest.marketstack.TradingApp.model.Trade
import quest.marketstack.TradingApp.model.TradeExec

import quest.marketstack.TradingApp.service.OpenTrades.OpenTradeServiceInterface

@RestController
@RequestMapping("/api/openTrades")
@CrossOrigin(origins = ["http://localhost:3000","http://192.168.86.244:3000"])
    class OpenTradeController(private val service: OpenTradeServiceInterface) {
    @GetMapping
    fun getTradeExecs(): Collection<Trade> = service.getTrades();

    @GetMapping("/{id}")
    fun getTradeExec(@PathVariable id: String) = service.getTrade(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addTradeExec(@RequestBody execList: Collection<TradeExec>): Collection<TradeExec>  = service.addTradeExecs(execList)
}