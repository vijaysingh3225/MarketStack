package quest.marketstack.TradingApp.datasource.TradeExec

import org.springframework.stereotype.Repository
import quest.marketstack.TradingApp.model.TradeExec

@Repository
interface ExecutionDataSource{

    fun retrieveTradeExecs(): Collection<TradeExec>
    fun retrieveTradeExec(id: String): TradeExec?
    fun createTradeExecs(execList: List<TradeExec>): List<TradeExec>


}