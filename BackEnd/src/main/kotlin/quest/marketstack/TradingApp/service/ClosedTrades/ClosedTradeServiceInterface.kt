package quest.marketstack.TradingApp.service.ClosedTrades

import quest.marketstack.TradingApp.model.ClosedTrade
import quest.marketstack.TradingApp.model.OpenTrade

interface ClosedTradeServiceInterface {
    fun getTrades(): Collection<ClosedTrade>
    fun getTrade(id: String): ClosedTrade?
}