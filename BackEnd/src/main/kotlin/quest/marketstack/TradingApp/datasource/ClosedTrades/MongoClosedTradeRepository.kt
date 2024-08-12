package quest.marketstack.TradingApp.datasource.ClosedTrades

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import quest.marketstack.TradingApp.model.Trade

@Repository
interface MongoClosedTradeRepository: MongoRepository<Trade, String> {
}