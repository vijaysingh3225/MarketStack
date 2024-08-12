package quest.marketstack.TradingApp.service.OpenTrades

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import quest.marketstack.TradingApp.datasource.OpenTrades.OpenTradeDataSource
import quest.marketstack.TradingApp.model.OpenTrade
import quest.marketstack.TradingApp.model.TradeExec

@Service
@Profile("test")
class OpenTradeService(private val dataSource: OpenTradeDataSource): OpenTradeServiceInterface {
    override fun getTrades(): Collection<OpenTrade> = dataSource.retrieveTrades()

    override fun getTrade(id: String): OpenTrade? = dataSource.retrieveTrade(id)

    override fun addTradeExecs(execList: Collection<TradeExec>): Collection<TradeExec> {
        val openTrades = dataSource.retrieveTrades()
        var isMatch = false
        for (i in execList) {
            for (j in openTrades) {
                val tradeId = j.id
                if (i.symbol == j.tradeExecs.first().symbol && tradeId != null) {
                    if ((j.shortLong&&(i.side=="SS"||i.side=="BC"))||(!j.shortLong&&(i.side=="B"||i.side=="S"))){
                        continue
                    }
                    print("Execution added to existing trade")
                    dataSource.addExec(i, tradeId)
                    isMatch = true
                }
            }
            if (!isMatch){
                print("New Trade Opened")
                if (i.side=="B")
                    dataSource.createTrades(listOf(OpenTrade(tradeExecs = mutableListOf(i), shortLong = true)))
                else
                    dataSource.createTrades(listOf(OpenTrade(tradeExecs = mutableListOf(i), shortLong = false)))
            }

        }
        //closeTrades(closedDataSource)
        return execList
    }
//    override fun closeTrades(closedDataSource: ClosedTradeDataSource): Collection<Trade> {
//        val openTrades = dataSource.retrieveTrades()
//        val closedTrades = mutableListOf<Trade>()
//        for (i in openTrades){
//            if (i.currentSize()==0){
//                closedTrades.add(i)
//            }
//        }
//        closedDataSource.createTrade(closedTrades)
//        return closedTrades
//    }
}