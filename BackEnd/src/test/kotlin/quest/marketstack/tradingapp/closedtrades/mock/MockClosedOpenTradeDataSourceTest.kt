@file:Suppress("ktlint:standard:no-wildcard-imports")

package quest.marketstack.tradingapp.closedtrades.mock

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import quest.marketstack.tradingapp.domain.mock.MockClosedTradeDataSource

class MockClosedOpenTradeDataSourceTest {
    private val mockDataSource = MockClosedTradeDataSource()

    @Test
    fun `should provide all closed trades in database`() {
        val mockTrades = mockDataSource.retrieveTrades()

        assertNotNull(mockTrades)
    }
}
