package quest.marketstack.TradingApp.service

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import quest.marketstack.TradingApp.datasource.TradeDataSource
import quest.marketstack.TradingApp.model.TradeExec

@Service
@Profile("test")
class TradeService(private val dataSource: TradeDataSource):TradeServiceInterface {
    override fun getTradeExecs(): Collection<TradeExec> = dataSource.retrieveTradeExecs()

    override fun getTradeExec(id: String): TradeExec? = dataSource.retrieveTradeExec(id)

    override fun addTradeExecs(execList: MutableList<TradeExec>): List<TradeExec> {
        val tradesInDB = dataSource.retrieveTradeExecs()
        val itemsToRemove = mutableListOf<TradeExec>()
            for (i in tradesInDB){
                for (j in execList){
                    if (isDuplicate(i,j)){
                        itemsToRemove.add(j)
                    }
                }
            }
        execList.removeAll(itemsToRemove)
        val length = itemsToRemove.size
        print("$length duplicate executions were found")
        return execList
    }
}