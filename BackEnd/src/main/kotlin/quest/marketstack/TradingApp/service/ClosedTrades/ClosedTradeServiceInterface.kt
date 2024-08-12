package quest.marketstack.TradingApp.service.ClosedTrades

import quest.marketstack.TradingApp.model.OpenTrade

interface ClosedTradeServiceInterface {
    fun getTrades(): Collection<OpenTrade>
    fun getTrade(id: String): OpenTrade?
}