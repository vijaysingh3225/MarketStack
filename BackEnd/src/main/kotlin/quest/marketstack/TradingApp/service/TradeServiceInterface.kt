package quest.marketstack.TradingApp.service

import quest.marketstack.TradingApp.model.TradeExec

interface TradeServiceInterface {
    fun getTradeExecs(): Collection<TradeExec>
    fun getTradeExec(id: String): TradeExec?
    fun addTradeExec(exec: TradeExec): TradeExec
}