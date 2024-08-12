package quest.marketstack.TradingApp.service.ClosedTrades

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import quest.marketstack.TradingApp.datasource.ClosedTrades.ClosedTradeDataSource
import quest.marketstack.TradingApp.model.OpenTrade

@Service
@Profile("Test")
class ClosedTradeService(private val dataSource: ClosedTradeDataSource):ClosedTradeServiceInterface {
    override fun getTrades(): Collection<OpenTrade> = dataSource.retrieveTrades()

    override fun getTrade(id: String): OpenTrade? = dataSource.retrieveTrade(id)
}