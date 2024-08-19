package quest.marketstack.tradingapp.service.opentrades

import quest.marketstack.tradingapp.domain.closedtrades.ClosedTradeDataSource
import quest.marketstack.tradingapp.model.ClosedTrade
import quest.marketstack.tradingapp.model.OpenTrade
import quest.marketstack.tradingapp.model.TradeExec

interface OpenTradeServiceInterface {
    fun getTrades(): Collection<OpenTrade>

    fun getTrade(id: String): OpenTrade?

    fun addTradeExecs(execList: Collection<TradeExec>): Collection<TradeExec>

    fun closeTrades(closedDataSource: ClosedTradeDataSource): Collection<ClosedTrade>
}
