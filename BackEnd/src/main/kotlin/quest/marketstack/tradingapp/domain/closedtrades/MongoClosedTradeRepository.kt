package quest.marketstack.tradingapp.domain.closedtrades

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import quest.marketstack.tradingapp.model.ClosedTrade

@Repository
interface MongoClosedTradeRepository : MongoRepository<ClosedTrade, String>
