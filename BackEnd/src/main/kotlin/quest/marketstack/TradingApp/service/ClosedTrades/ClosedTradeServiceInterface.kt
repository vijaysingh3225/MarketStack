package quest.marketstack.TradingApp.service.ClosedTrades

import quest.marketstack.TradingApp.model.Trade

interface ClosedTradeServiceInterface {
    fun getTrades(): Collection<Trade>
    fun getTrade(id: String): Trade?
}