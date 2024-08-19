package quest.marketstack.tradingapp.domain.opentrades

import org.springframework.stereotype.Repository
import quest.marketstack.tradingapp.model.OpenTrade
import quest.marketstack.tradingapp.model.TradeExec

@Repository
interface OpenTradeDataSource {
    fun retrieveTrades(): Collection<OpenTrade>

    fun retrieveTrade(id: String): OpenTrade?

    fun createTrades(execList: Collection<OpenTrade>): Collection<OpenTrade>

    fun deleteTrade(id: String): OpenTrade?

    fun addExec(
        exec: TradeExec,
        id: String,
    ): OpenTrade?
}
