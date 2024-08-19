package quest.marketstack.tradingapp.service.closedtrades

import quest.marketstack.tradingapp.model.ClosedTrade

interface ClosedTradeServiceInterface {
    fun getTrades(): Collection<ClosedTrade>

    fun getTrade(id: String): ClosedTrade?
}
