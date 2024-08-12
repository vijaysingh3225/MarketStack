package quest.marketstack.TradingApp.service.ClosedTrades

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import quest.marketstack.TradingApp.datasource.ClosedTrades.ClosedTradeDataSource
import quest.marketstack.TradingApp.model.ClosedTrade

@Service
@Profile("!Test")
class MongoClosedTradeService(private val dataSource: ClosedTradeDataSource):ClosedTradeServiceInterface {
    override fun getTrades(): Collection<ClosedTrade> = dataSource.retrieveTrades()

    override fun getTrade(id: String): ClosedTrade? = dataSource.retrieveTrade(id)
}