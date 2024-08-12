package quest.marketstack.TradingApp.TradeExec.service

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import quest.marketstack.TradingApp.datasource.TradeExec.TradeExecDataSource
import quest.marketstack.TradingApp.service.TradeService

private val dataSource: TradeExecDataSource = mockk(relaxed = true)

private val tradeService = TradeService(dataSource)

class TradeServiceTest{
    @Test
    fun `should call it's data source to retrieve banks`() {

        val trades = tradeService.getTrades()

        verify(exactly = 1){ dataSource.retrieveTrades()}
    }
}