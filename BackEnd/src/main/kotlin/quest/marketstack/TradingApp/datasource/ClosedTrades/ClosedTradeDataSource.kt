package quest.marketstack.TradingApp.datasource.ClosedTrades

import org.springframework.stereotype.Repository
import quest.marketstack.TradingApp.model.Trade

@Repository
interface ClosedTradeDataSource {
    fun retrieveTrades(): Collection<Trade>
    fun retrieveTrade(id: String): Trade?
    fun createTrade(execList: Collection<Trade>): Collection<Trade>

}