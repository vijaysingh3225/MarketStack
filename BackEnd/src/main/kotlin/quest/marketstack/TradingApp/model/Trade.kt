package quest.marketstack.TradingApp.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Trade(
            @Id
            val id: String? = null,
            val tradeExecs: Array<TradeExec>,
            val winLossBoolean: Boolean,
            val profitLoss: Double,
            val maxPosition: Int,
            val shortLong: String,
            val executions: Int)

