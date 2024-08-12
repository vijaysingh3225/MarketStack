package quest.marketstack.TradingApp.datasource.OpenTrades

import org.springframework.stereotype.Repository
import quest.marketstack.TradingApp.model.OpenTrade
import quest.marketstack.TradingApp.model.TradeExec

@Repository
interface OpenTradeDataSource{

    fun retrieveTrades(): Collection<OpenTrade>
    fun retrieveTrade(id: String): OpenTrade?
    fun createTrades(execList: Collection<OpenTrade>): Collection<OpenTrade>
    fun addExec(exec: TradeExec, id: String):OpenTrade?

}