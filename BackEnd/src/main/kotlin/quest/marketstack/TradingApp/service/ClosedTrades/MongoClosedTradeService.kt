package quest.marketstack.TradingApp.service.ClosedTrades

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import quest.marketstack.TradingApp.datasource.mock.MockClosedTradeDataSource
import quest.marketstack.TradingApp.model.Trade

@Service
@Profile("Test")
class MongoClosedTradeService(private val dataSource: MockClosedTradeDataSource):ClosedTradeServiceInterface {
    override fun getTrades(): Collection<Trade> = dataSource.retrieveTrades()

    override fun getTrade(id: String): Trade? = dataSource.retrieveTrade(id)
}