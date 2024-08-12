package quest.marketstack.TradingApp.datasource.OpenTrades

import org.springframework.stereotype.Repository
import quest.marketstack.TradingApp.model.Trade
import quest.marketstack.TradingApp.model.TradeExec

@Repository
interface OpenTradeDataSource{

    fun retrieveTrades(): Collection<Trade>
    fun retrieveTrade(id: String): Trade?
    fun createTrades(execList: Collection<Trade>): Collection<Trade>
    fun addExec(exec: TradeExec, id: String):Trade?

}