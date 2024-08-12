package quest.marketstack.TradingApp.service

import quest.marketstack.TradingApp.model.Trade
import quest.marketstack.TradingApp.model.TradeExec

interface TradeServiceInterface {
    fun getTrades(): Collection<Trade>
    fun getTrade(id: String): Trade?
    fun addTradeExecs(execList: Collection<TradeExec>): Collection<TradeExec>

}