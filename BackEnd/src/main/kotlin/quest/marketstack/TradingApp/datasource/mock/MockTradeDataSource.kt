package quest.marketstack.TradingApp.datasource.mock

import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import quest.marketstack.TradingApp.datasource.TradeDataSource
import quest.marketstack.TradingApp.model.TradeExec
import java.time.LocalDate
import java.time.LocalTime

@Component
@Profile("test")
class MockTradeDataSource:TradeDataSource {

    val mockExec = mutableListOf(TradeExec(
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
        note = ""))

    override fun retrieveTradeExecs(): Collection<TradeExec>{
        return mockExec
    }
    override fun retrieveTradeExec(id: String): TradeExec? {
        return mockExec.firstOrNull() { it.id == id }

        }

    override fun createTradeExecs(execList: List<TradeExec>): List<TradeExec>{
        mockExec.addAll(execList)
        return execList
    }
}