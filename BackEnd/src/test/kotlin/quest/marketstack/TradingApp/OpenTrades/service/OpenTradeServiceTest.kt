package quest.marketstack.TradingApp.OpenTrades.service

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import quest.marketstack.TradingApp.datasource.OpenTrades.OpenTradeDataSource
import quest.marketstack.TradingApp.service.OpenTrades.OpenTradeService

private val dataSource: OpenTradeDataSource = mockk(relaxed = true)

private val tradeService = OpenTradeService(dataSource)

class OpenTradeServiceTest{
    @Test
    fun `should call it's data source to retrieve open trades`() {

        val trades = tradeService.getTrades()

        verify(exactly = 1){ dataSource.retrieveTrades()}
    }
}