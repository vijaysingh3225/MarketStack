package quest.marketstack.TradingApp.datasource

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import quest.marketstack.TradingApp.model.TradeExec

@Repository
interface TradeDataSource{

    fun retrieveTradeExecs(): Collection<TradeExec>
    fun retrieveTradeExec(id: String): TradeExec?
    fun createTradeExecs(execList: List<TradeExec>): List<TradeExec>


}