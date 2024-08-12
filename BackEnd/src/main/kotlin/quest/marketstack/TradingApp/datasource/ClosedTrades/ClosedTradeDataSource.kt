package quest.marketstack.TradingApp.datasource.ClosedTrades

import org.springframework.stereotype.Repository
import quest.marketstack.TradingApp.model.ClosedTrade
import quest.marketstack.TradingApp.model.OpenTrade

@Repository
interface ClosedTradeDataSource {
    fun retrieveTrades(): Collection<ClosedTrade>
    fun retrieveTrade(id: String): ClosedTrade?
    fun createTrade(execList: Collection<ClosedTrade>): Collection<ClosedTrade>

}