package quest.marketstack.TradingApp.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import quest.marketstack.TradingApp.model.TradeExec

import quest.marketstack.TradingApp.service.TradeServiceInterface

@RestController
@RequestMapping("/api/tradeExecs")
@CrossOrigin(origins = ["http://localhost:3000","http://192.168.86.244:3000"])
    class TradeController(private val service: TradeServiceInterface) {
    @GetMapping
    fun getTradeExecs(): Collection<TradeExec> = service.getTradeExecs();

    @GetMapping("/{id}")
    fun getTradeExec(@PathVariable id: String) = service.getTradeExec(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addTradeExec(@RequestBody exec: TradeExec): TradeExec = service.addTradeExec(exec)
}