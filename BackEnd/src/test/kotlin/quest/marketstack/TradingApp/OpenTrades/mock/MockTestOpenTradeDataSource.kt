package quest.marketstack.TradingApp.OpenTrades.mock

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import quest.marketstack.TradingApp.datasource.mock.MockOpenTradeDataSource


internal class MockTestOpenTradeDataSource {

    private val mockDataSource = MockOpenTradeDataSource();

    @Test
    fun `should provide a collection of trade executions`() {

        val tradeExecs = mockDataSource.retrieveTrades()

        Assertions.assertThat(tradeExecs).isNotEmpty()
    }
    @Test
    fun `should provide some mock data`() {
        val tradeExecs = mockDataSource.retrieveTrades()

        assertNotNull(tradeExecs)
    }
}
