package quest.marketstack.TradingApp.service.OpenTrades

import quest.marketstack.TradingApp.model.Trade
import quest.marketstack.TradingApp.model.TradeExec

interface OpenTradeServiceInterface {
    fun getTrades(): Collection<Trade>
    fun getTrade(id: String): Trade?
    fun addTradeExecs(execList: Collection<TradeExec>): Collection<TradeExec>

}