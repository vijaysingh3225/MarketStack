package quest.marketstack.tradingapp.domain.mock

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import quest.marketstack.tradingapp.domain.opentrades.OpenTradeDataSource
import quest.marketstack.tradingapp.model.OpenTrade
import quest.marketstack.tradingapp.model.TradeExec
import java.time.LocalDate
import java.time.LocalTime

@Component
@Profile("test")
class MockOpenTradeDataSource : OpenTradeDataSource {
    val mockExec =
        mutableListOf(
            TradeExec(
                id = "234",
                account = "2313",
                tradeDate = LocalDate.of(2022, 7, 15),
                settlementDate = LocalDate.of(2021, 9, 15),
                currency = "USD",
                type = 3,
                side = "SS",
                symbol = "UROY",
                quantity = 200,
                price = 4.3038,
                execTime = LocalTime.of(14, 45, 30),
                commission = 0.0,
                secFee = 0.01,
                taf = 0.02,
                nscc = 0.0,
                nasdaq = 0.0,
                ecnRemove = 0.0,
                ecnAdd = 0.0,
                grossProceeds = 860.76,
                netProceeds = 860.73,
                clearingBroker = "MNGD",
                liquidity = "",
                note = "",
            ),
            TradeExec(
                id = "629",
                account = "2313",
                tradeDate = LocalDate.of(2014, 3, 24),
                settlementDate = LocalDate.of(2021, 9, 15),
                currency = "USD",
                type = 3,
                side = "SS",
                symbol = "UROY",
                quantity = 200,
                price = 4.3038,
                execTime = LocalTime.of(14, 45, 30),
                commission = 0.0,
                secFee = 0.01,
                taf = 0.02,
                nscc = 0.0,
                nasdaq = 0.0,
                ecnRemove = 0.0,
                ecnAdd = 0.0,
                grossProceeds = 860.76,
                netProceeds = 860.73,
                clearingBroker = "MNGD",
                liquidity = "",
                note = "",
            ),
        )
    val mockTrade = mutableListOf(OpenTrade(id = "123", tradeExecs = mockExec, shortLong = false))

    override fun retrieveTrades(): List<OpenTrade> = mockTrade

    override fun retrieveTrade(id: String): OpenTrade? = mockTrade.firstOrNull { it.id == id }

    override fun createTrades(execList: Collection<OpenTrade>): Collection<OpenTrade> {
        mockTrade.addAll(execList)
        return execList
    }

    override fun deleteTrade(id: String): OpenTrade? {
        var index = 0
        var deletedTrade: OpenTrade? = null
        for (i in mockTrade) {
            if (i.id == id) {
                mockTrade.removeAt(index)
                deletedTrade = i
            }
            index++
        }
        return deletedTrade
    }

    override fun addExec(
        exec: TradeExec,
        id: String,
    ): OpenTrade? {
        for (i in mockTrade) {
            if (i.id == id) {
                i.tradeExecs.add(exec)
                return i
            }
        }
        return null
    }
}
