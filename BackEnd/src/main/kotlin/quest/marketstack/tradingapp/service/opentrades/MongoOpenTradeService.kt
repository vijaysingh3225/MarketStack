package quest.marketstack.tradingapp.service.opentrades

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import quest.marketstack.tradingapp.domain.closedtrades.ClosedTradeDataSource
import quest.marketstack.tradingapp.domain.opentrades.MongoOpenTradeDataSource
import quest.marketstack.tradingapp.model.ClosedTrade
import quest.marketstack.tradingapp.model.OpenTrade
import quest.marketstack.tradingapp.model.TradeExec

@Service
@Profile("!test")
class MongoOpenTradeService(
    private val dataSource: MongoOpenTradeDataSource,
    private val closedDataSource: ClosedTradeDataSource,
) : OpenTradeServiceInterface {
    override fun getTrades(): Collection<OpenTrade> = dataSource.retrieveTrades()

    override fun getTrade(id: String): OpenTrade? = dataSource.retrieveTrade(id)

    override fun addTradeExecs(execList: Collection<TradeExec>): Collection<TradeExec> {
        var openTrades = dataSource.retrieveTrades()
        var isMatch = false
        for (i in execList) {
            openTrades = dataSource.retrieveTrades()
            isMatch = false
            for (j in openTrades) {
                val tradeId = j.id
                if (i.symbol == j.tradeExecs.first().symbol && tradeId != null) {
                    if ((j.shortLong && (i.side == "SS" || i.side == "BC")) || (!j.shortLong && (i.side == "B" || i.side == "S"))) {
                        continue
                    }
                    print("Execution added to existing trade")
                    dataSource.addExec(i, tradeId)
                    isMatch = true
                }
            }
            if (!isMatch) {
                print("New Trade Opened")
                if (i.side == "B") {
                    dataSource.createTrades(listOf(OpenTrade(tradeExecs = mutableListOf(i), shortLong = true)))
                }
                if (i.side == "SS") {
                    dataSource.createTrades(listOf(OpenTrade(tradeExecs = mutableListOf(i), shortLong = false)))
                }
            }
            closeTrades(closedDataSource)
        }

        return execList
    }

    override fun closeTrades(closedDataSource: ClosedTradeDataSource): Collection<ClosedTrade> {
        val openTrades = dataSource.retrieveTrades()
        val closedTrades = mutableListOf<ClosedTrade>()
        for (i in openTrades) {
            if (i.currentSize == 0) {
                i.id?.let { dataSource.deleteTrade(it) }
                closedDataSource.createTrade(listOf(i.covertToClosed(i)))
            }
        }
        closedDataSource.createTrade(closedTrades)
        return closedTrades
    }
}
