package quest.marketstack.TradingApp.datasource.TradeExec

import org.springframework.stereotype.Repository
import quest.marketstack.TradingApp.model.Trade
import quest.marketstack.TradingApp.model.TradeExec

@Repository
interface TradeExecDataSource{

    fun retrieveTrades(): Collection<Trade>
    fun retrieveTrade(id: String): Trade?
    fun createTrades(execList: Collection<Trade>): Collection<Trade>
    fun addExec(exec: TradeExec, id: String):Trade

}