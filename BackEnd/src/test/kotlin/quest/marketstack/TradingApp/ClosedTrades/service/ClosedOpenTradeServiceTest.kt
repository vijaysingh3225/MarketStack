package quest.marketstack.TradingApp.ClosedTrades.service

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import quest.marketstack.TradingApp.datasource.ClosedTrades.ClosedTradeDataSource
import quest.marketstack.TradingApp.service.ClosedTrades.ClosedTradeService


class ClosedOpenTradeServiceTest{
    private val dataSource: ClosedTradeDataSource = mockk(relaxed = true)

    private val tradeService = ClosedTradeService(dataSource)

    @Test
    fun `should call it's data source to retrieve closed trades`() {
        val trades = tradeService.getTrades()

        assertNotNull(trades)
    }
}