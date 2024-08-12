package quest.marketstack.TradingApp.datasource.OpenTrades

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import quest.marketstack.TradingApp.model.OpenTrade

@Repository
interface MongoOpenTradeRepository : MongoRepository<OpenTrade, String> {

}