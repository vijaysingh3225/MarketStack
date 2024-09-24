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

        val allExistingTradeExecs: MutableSet<TradeExec> = mutableSetOf()

        openTrades.forEach { openTrade ->
            allExistingTradeExecs.addAll(openTrade.tradeExecs)
        }
        val closedTrades = closedDataSource.retrieveTrades()
        closedTrades.forEach { closedTrade ->
            allExistingTradeExecs.addAll(closedTrade.tradeExecs)
        }

        val nonDuplicateExecs = execList.filter { !allExistingTradeExecs.contains(it) }

        val numberOfDuplicates = execList.size - nonDuplicateExecs.size
        println("Number of duplicates found: $numberOfDuplicates")

        for (exec in nonDuplicateExecs) {
            openTrades = dataSource.retrieveTrades()
            isMatch = false

            for (openTrade in openTrades) {
                val tradeId = openTrade.id
                if (exec.symbol == openTrade.tradeExecs.firstOrNull()?.symbol && tradeId != null) {
                    if ((openTrade.shortLong && (exec.side == "SS" || exec.side == "BC")) ||
                        (!openTrade.shortLong && (exec.side == "B" || exec.side == "S"))
                    ) {
                        println("IGNORED")
                        continue
                    }
                    println("Execution added to existing trade")
                    dataSource.addExec(exec, tradeId)
                    isMatch = true
                    break
                }
            }

            if (!isMatch) {
                println("New Trade Opened")
                if (exec.side == "B") {
                    dataSource.createTrades(listOf(OpenTrade(tradeExecs = mutableListOf(exec), shortLong = true)))
                }
                if (exec.side == "SS") {
                    dataSource.createTrades(listOf(OpenTrade(tradeExecs = mutableListOf(exec), shortLong = false)))
                }
            }
            closeTrades(closedDataSource)
        }

        return nonDuplicateExecs
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
