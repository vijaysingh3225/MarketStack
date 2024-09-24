package quest.marketstack.tradingapp.closedtrades.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import quest.marketstack.tradingapp.domain.closedtrades.ClosedTradeDataSource
import quest.marketstack.tradingapp.model.ClosedTrade
import quest.marketstack.tradingapp.service.closedtrades.ClosedTradeService

class ClosedOpenTradeServiceTest {
    private val dataSource: ClosedTradeDataSource = mockk()

    private val tradeService = ClosedTradeService(dataSource)

    @Test
    fun `getTrades() should call it's data source to retrieve closed trades`() {
        val testClosedTrades = getTestClosedTrades()

        every {
            dataSource.retrieveTrades()
        } returns testClosedTrades

        val trades = tradeService.getTrades()

        assertNotNull(trades)
        verify(exactly = 1) {
            dataSource.retrieveTrades()
        }
    }

    private fun getTestClosedTrade(): ClosedTrade =
        ClosedTrade(
            id = "1",
            tradeExecs = mutableListOf(),
            shortLong = true,
        )

    private fun getTestClosedTrades(): List<ClosedTrade> = listOf(getTestClosedTrade())
}
