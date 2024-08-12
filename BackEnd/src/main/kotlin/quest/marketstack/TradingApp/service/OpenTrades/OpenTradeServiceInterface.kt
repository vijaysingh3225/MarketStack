package quest.marketstack.TradingApp.service.OpenTrades

import quest.marketstack.TradingApp.model.OpenTrade
import quest.marketstack.TradingApp.model.TradeExec

interface OpenTradeServiceInterface {
    fun getTrades(): Collection<OpenTrade>
    fun getTrade(id: String): OpenTrade?
    fun addTradeExecs(execList: Collection<TradeExec>): Collection<TradeExec>
    //fun closeTrades(closedDataSource:ClosedTradeDataSource): Collection<Trade>

}