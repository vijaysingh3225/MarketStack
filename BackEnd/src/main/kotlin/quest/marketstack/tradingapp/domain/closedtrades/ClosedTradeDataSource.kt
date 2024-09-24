package quest.marketstack.tradingapp.domain.closedtrades

import org.springframework.stereotype.Repository
import quest.marketstack.tradingapp.model.ClosedTrade

@Repository
interface ClosedTradeDataSource {
    fun retrieveTrades(): List<ClosedTrade>

    fun retrieveTrade(id: String): ClosedTrade?

    fun createTrade(execList: Collection<ClosedTrade>): Collection<ClosedTrade>
}
