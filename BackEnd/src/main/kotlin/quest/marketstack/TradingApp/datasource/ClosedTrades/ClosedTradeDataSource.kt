package quest.marketstack.TradingApp.datasource.ClosedTrades

import org.springframework.stereotype.Repository
import quest.marketstack.TradingApp.model.OpenTrade

@Repository
interface ClosedTradeDataSource {
    fun retrieveTrades(): Collection<OpenTrade>
    fun retrieveTrade(id: String): OpenTrade?
    fun createTrade(execList: Collection<OpenTrade>): Collection<OpenTrade>

}