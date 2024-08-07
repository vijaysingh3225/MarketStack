package quest.marketstack.TradingApp.datasource.mock

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class MockTradeDataSourceTest {

    private val mockDataSource = MockTradeDataSource();

    @Test
    fun `should provide a collection of trade executions`() {

        val tradeExecs = mockDataSource.retrieveTradeExecs()

        Assertions.assertThat(tradeExecs).isNotEmpty()
    }
    @Test
    fun `should provide some mock data`() {
        val tradeExecs = mockDataSource.retrieveTradeExecs()

        assertNotNull(tradeExecs)

    }
}
