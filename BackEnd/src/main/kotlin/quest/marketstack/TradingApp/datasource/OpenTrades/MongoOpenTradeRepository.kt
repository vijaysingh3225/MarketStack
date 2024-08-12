package quest.marketstack.TradingApp.datasource.OpenTrades

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import quest.marketstack.TradingApp.model.Trade

@Repository
interface MongoOpenTradeRepository : MongoRepository<Trade, String> {

}