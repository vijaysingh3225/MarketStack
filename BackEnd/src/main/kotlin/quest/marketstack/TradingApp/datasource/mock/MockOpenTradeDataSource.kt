package quest.marketstack.TradingApp.datasource.mock

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import quest.marketstack.TradingApp.datasource.OpenTrades.OpenTradeDataSource
import quest.marketstack.TradingApp.model.OpenTrade
import quest.marketstack.TradingApp.model.TradeExec
import java.time.LocalDate
import java.time.LocalTime

@Component
@Profile("test")
class MockOpenTradeDataSource: OpenTradeDataSource {

    val mockExec = mutableListOf(
        TradeExec(
            id = "234",
            account = "2313",
            tradeDate = LocalDate.of(2022, 7, 15),
            settlementDate = LocalDate.of(2021, 9, 15),
            currency = "USD",
            type = 3, // Example type
            side = "SS", // Side could be 'Buy' or 'Sell'
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
            liquidity = "", // Empty string for liquidity
            note = ""
        )
    )
    val mockTrade = mutableListOf(OpenTrade(id = "123", tradeExecs = mockExec, shortLong = false))

    override fun retrieveTrades(): Collection<OpenTrade> {
        return mockTrade
    }

    override fun retrieveTrade(id: String): OpenTrade? {
        return mockTrade.firstOrNull() { it.id == id }

    }

    override fun createTrades(execList: Collection<OpenTrade>): Collection<OpenTrade> {
        mockTrade.addAll(execList)
        return execList
    }

    override fun addExec(exec: TradeExec, id: String): OpenTrade? {
        for (i in mockTrade){
            if (i.id == id) {
                i.tradeExecs.add(exec)
                return i
            }
        }
    return null

    }
}