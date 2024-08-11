package quest.marketstack.TradingApp.service

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import quest.marketstack.TradingApp.datasource.TradeExec.ExecutionDataSource

private val dataSource: ExecutionDataSource = mockk(relaxed = true)

private val tradeService = TradeService(dataSource)

class TradeServiceTest{
    @Test
    fun `should call it's data source to retrieve banks`() {

        val trades = tradeService.getTradeExecs()

        verify(exactly = 1){ dataSource.retrieveTradeExecs()}
    }
}