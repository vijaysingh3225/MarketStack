package quest.marketstack.TradingApp.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import quest.marketstack.TradingApp.datasource.TradeDataSource

private val dataSource: TradeDataSource = mockk(relaxed = true)

private val tradeService = TradeService(dataSource)

class TradeServiceTest{
    @Test
    fun `should call it's data source to retrieve banks`() {

        val trades = tradeService.getTradeExecs()

        verify(exactly = 1){ dataSource.retrieveTradeExecs()}
    }
}