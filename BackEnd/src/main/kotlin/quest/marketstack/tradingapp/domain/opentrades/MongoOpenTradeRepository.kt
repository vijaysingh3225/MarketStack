package quest.marketstack.tradingapp.domain.opentrades

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import quest.marketstack.tradingapp.model.OpenTrade

@Repository
interface MongoOpenTradeRepository : MongoRepository<OpenTrade, String>
