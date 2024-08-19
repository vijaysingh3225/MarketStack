package quest.marketstack.tradingapp.service.closedtrades

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import quest.marketstack.tradingapp.domain.closedtrades.ClosedTradeDataSource
import quest.marketstack.tradingapp.model.ClosedTrade

@Service
@Profile("Test")
class ClosedTradeService(
    private val dataSource: ClosedTradeDataSource,
) : ClosedTradeServiceInterface {
    override fun getTrades(): Collection<ClosedTrade> = dataSource.retrieveTrades()

    override fun getTrade(id: String): ClosedTrade? = dataSource.retrieveTrade(id)
}
