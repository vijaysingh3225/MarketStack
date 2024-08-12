package quest.marketstack.TradingApp.datasource.ClosedTrades

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import quest.marketstack.TradingApp.model.ClosedTrade
import quest.marketstack.TradingApp.model.OpenTrade

@Repository
interface MongoClosedTradeRepository: MongoRepository<ClosedTrade, String> {
}