package quest.marketstack.TradingApp.service.OpenTrades


import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import quest.marketstack.TradingApp.datasource.OpenTrades.MongoOpenTradeDataSource
import quest.marketstack.TradingApp.model.Trade
import quest.marketstack.TradingApp.model.TradeExec

@Service
@Profile("!test")
class MongoOpenTradeService(private val dataSource: MongoOpenTradeDataSource): OpenTradeServiceInterface {
    override fun getTrades(): Collection<Trade> = dataSource.retrieveTrades()

    override fun getTrade(id: String): Trade? = dataSource.retrieveTrade(id)

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
                    dataSource.addExec(i, tradeId)
                    isMatch = true
                }
            }
            if (!isMatch){
                if (i.side=="B")
                    dataSource.createTrades(listOf(Trade(tradeExecs = mutableListOf(i), shortLong = true)))
                else
                    dataSource.createTrades(listOf(Trade(tradeExecs = mutableListOf(i), shortLong = false)))
            }

        }
        return execList
    }

}