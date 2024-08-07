package quest.marketstack.TradingApp.datasource
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import quest.marketstack.TradingApp.model.TradeExec

@Repository
interface TradeExecRepository : MongoRepository<TradeExec, String> {

}