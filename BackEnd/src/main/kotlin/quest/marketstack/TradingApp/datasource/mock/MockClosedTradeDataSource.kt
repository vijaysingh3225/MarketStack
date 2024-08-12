package quest.marketstack.TradingApp.datasource.mock

import quest.marketstack.TradingApp.datasource.ClosedTrades.ClosedTradeDataSource
import quest.marketstack.TradingApp.model.Trade
import quest.marketstack.TradingApp.model.TradeExec
import java.time.LocalDate
import java.time.LocalTime

class MockClosedTradeDataSource : ClosedTradeDataSource {
    val mockExec = mutableListOf(
        TradeExec(
            id = "234", account = "2313", tradeDate = LocalDate.of(2022, 7, 15), settlementDate = LocalDate.of(2021, 9, 15),
            currency = "USD", type = 3, side = "SS", symbol = "MRIN", quantity = 200, price = 4.3038, execTime = LocalTime.of(14, 45, 30), commission = 0.0,
            secFee = 0.01, taf = 0.02, nscc = 0.0, nasdaq = 0.0, ecnRemove = 0.0, ecnAdd = 0.0, grossProceeds = 860.76, netProceeds = 860.73,
            clearingBroker = "MNGD", liquidity = "", note = ""
        ),
        TradeExec(
            id = "234", account = "2313", tradeDate = LocalDate.of(2022, 7, 15), settlementDate = LocalDate.of(2021, 9, 15),
            currency = "USD", type = 3, side = "BC", symbol = "MRIN", quantity = 200, price = 4.3038, execTime = LocalTime.of(14, 45, 30), commission = 0.0,
            secFee = 0.01, taf = 0.02, nscc = 0.0, nasdaq = 0.0, ecnRemove = 0.0, ecnAdd = 0.0, grossProceeds = 860.76, netProceeds = 860.73,
            clearingBroker = "MNGD", liquidity = "", note = ""
        )
    )
    val mockTrade = mutableListOf(Trade(id = "123", tradeExecs = mockExec, shortLong = false))
    override fun retrieveTrades(): Collection<Trade> {
        return mockTrade
    }

    override fun retrieveTrade(id: String): Trade? {
        return mockTrade.firstOrNull() { it.id == id }
    }

    override fun createTrade(execList: Collection<Trade>): Collection<Trade> {
        mockTrade.addAll(execList)
        return execList
    }
}