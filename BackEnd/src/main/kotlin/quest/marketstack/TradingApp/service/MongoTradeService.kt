package quest.marketstack.TradingApp.service


import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import quest.marketstack.TradingApp.datasource.TradeExec.MongoExecDataSource
import quest.marketstack.TradingApp.model.Trade
import quest.marketstack.TradingApp.model.TradeExec

@Service
@Profile("!test")
class MongoTradeService(private val dataSource: MongoExecDataSource): TradeServiceInterface {
    override fun getTrades(): Collection<Trade> = dataSource.retrieveTrades()

    override fun getTrade(id: String): Trade? = dataSource.retrieveTrade(id)

    override fun addTradeExecs(execList: Collection<TradeExec>): Collection<TradeExec> {
        val openTrades = dataSource.retrieveTrades()

        for (i in execList) {
            var isMatch = false

            for (j in openTrades) {
                val tradeId = j.id
                if (i.symbol == j.tradeExecs.firstOrNull()?.symbol && tradeId != null) {
                    dataSource.addExec(i, tradeId)
                    isMatch = true
                    break
                }
            }

            if (!isMatch) {
                val newTrade = Trade(
                    tradeExecs = mutableListOf(i),
                    shortLong = i.side == "B" // Assuming side "B" indicates long trades
                )
                dataSource.createTrades(listOf(newTrade))
            }
        }

        return execList
    }

}