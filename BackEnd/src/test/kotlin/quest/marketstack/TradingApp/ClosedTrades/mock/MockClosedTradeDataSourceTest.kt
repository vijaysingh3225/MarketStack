package quest.marketstack.TradingApp.ClosedTrades.mock

import quest.marketstack.TradingApp.datasource.mock.MockClosedTradeDataSource
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MockClosedTradeDataSourceTest{
    private val mockDataSource = MockClosedTradeDataSource()

    @Test
    fun `should provide all closed trades in database`() {
        val mockTrades = mockDataSource.retrieveTrades()

        assertNotNull(mockTrades)
    }
}