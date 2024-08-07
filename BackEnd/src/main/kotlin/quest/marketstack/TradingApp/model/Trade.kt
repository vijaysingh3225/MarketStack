package quest.marketstack.TradingApp.model

data class Trade(val tradeExecs: Array<TradeExec>,
                val winLossBoolean: Boolean,
                val profitLoss: Double,
                val maxPosition: Int,
                val shortLong: String,
                val executions: Int)

