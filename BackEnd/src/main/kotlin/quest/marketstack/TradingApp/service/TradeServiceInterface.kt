package quest.marketstack.TradingApp.service

import quest.marketstack.TradingApp.model.TradeExec

interface TradeServiceInterface {
    fun getTradeExecs(): Collection<TradeExec>
    fun getTradeExec(id: String): TradeExec?
    fun addTradeExecs(execList: MutableList<TradeExec>): List<TradeExec>
    fun isDuplicate (first:TradeExec, second:TradeExec): Boolean{
        return (first.tradeDate==second.tradeDate&&first.execTime==second.execTime)
    }

}