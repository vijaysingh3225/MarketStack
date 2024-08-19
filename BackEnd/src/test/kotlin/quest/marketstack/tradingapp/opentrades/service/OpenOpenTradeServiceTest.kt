package quest.marketstack.tradingapp.opentrades.service

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import quest.marketstack.tradingapp.domain.closedtrades.ClosedTradeDataSource
import quest.marketstack.tradingapp.domain.opentrades.OpenTradeDataSource
import quest.marketstack.tradingapp.service.opentrades.OpenTradeService

private val dataSource: OpenTradeDataSource = mockk(relaxed = true)
private val closedDataSource: ClosedTradeDataSource = mockk(relaxed = true)

private val tradeService = OpenTradeService(dataSource, closedDataSource)

class OpenOpenTradeServiceTest {
    @Test
    fun `should call it's data source to retrieve open trades`() {
        val trades = tradeService.getTrades()

        verify(exactly = 1) { dataSource.retrieveTrades() }
    }
}
