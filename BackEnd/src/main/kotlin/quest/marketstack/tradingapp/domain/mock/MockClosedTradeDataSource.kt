package quest.marketstack.tradingapp.domain.mock

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import quest.marketstack.tradingapp.domain.closedtrades.ClosedTradeDataSource
import quest.marketstack.tradingapp.model.ClosedTrade
import quest.marketstack.tradingapp.model.TradeExec
import java.time.LocalDate
import java.time.LocalTime

@Component
@Profile("test")
class MockClosedTradeDataSource : ClosedTradeDataSource {
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
                symbol = "MRIN",
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
                id = "434",
                account = "2313",
                tradeDate = LocalDate.of(2022, 7, 15),
                settlementDate = LocalDate.of(2021, 9, 15),
                currency = "USD",
                type = 3,
                side = "BC",
                symbol = "MRIN",
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
    val mockTrade = mutableListOf(ClosedTrade(id = "123", tradeExecs = mockExec, shortLong = false))

    override fun retrieveTrades(): List<ClosedTrade> = mockTrade

    override fun retrieveTrade(id: String): ClosedTrade? = mockTrade.firstOrNull { it.id == id }

    override fun createTrade(execList: Collection<ClosedTrade>): Collection<ClosedTrade> {
        mockTrade.addAll(execList)
        return execList
    }
}
