package quest.marketstack.TradingApp.service


import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import quest.marketstack.TradingApp.datasource.TradeExec.MongoExecutionDataSource
import quest.marketstack.TradingApp.model.TradeExec

@Service
@Profile("!test")
class MongoTradeService(private val dataSource: MongoExecutionDataSource): TradeServiceInterface {
    override fun getTradeExecs(): Collection<TradeExec> = dataSource.retrieveTradeExecs()

    override fun getTradeExec(id: String): TradeExec? = dataSource.retrieveTradeExec(id)

    override fun addTradeExecs(execList: MutableList<TradeExec>):List<TradeExec> = dataSource.createTradeExecs(execList)
}