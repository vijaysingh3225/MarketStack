package quest.marketstack.tradingapp.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "\${api.closed-trade.collection}")
data class ClosedTrade(
    @Id
    val id: String? = null,
    var tradeExecs: MutableCollection<TradeExec> = mutableListOf(),
    val shortLong: Boolean,
) {
    val maxPosition: Int get() {
        var max: Int = 0
        var current: Int = 0
        for (i in tradeExecs) {
            if (i.side == "B" || i.side == "SS") {
                current += i.quantity
                if (current > max) {
                    max = current
                }
            } else {
                current -= i.quantity
            }
        }
        return max
    }

    val profitLoss: Double get() {
        var openAvg = 0.0
        var closeAvg = 0.0
        var openShares = 0
        var closeShares = 0

        for (i in tradeExecs) {
            if (i.side == "B" || i.side == "BC") { // Buy or Buy to Cover
                openShares += i.quantity
                openAvg += (i.quantity * i.price)
            } else { // Sell or Short Sell
                closeShares += i.quantity
                closeAvg += (i.quantity * i.price)
            }
        }

        // Calculate averages
        openAvg = if (openShares != 0) openAvg / openShares else 0.0
        closeAvg = if (closeShares != 0) closeAvg / closeShares else 0.0

        // Calculate profit/loss
        return (closeAvg - openAvg) * minOf(openShares, closeShares)
    }

    fun addExec(newExec: TradeExec) {
        tradeExecs.add(newExec)
    }

    val currentSize: Int get() {
        var size = 0
        if (shortLong) {
            for (i in tradeExecs) {
                if (i.side == "B") {
                    size += i.quantity
                } else {
                    size -= i.quantity
                }
            }
        } else {
            for (i in tradeExecs) {
                if (i.side == "SS") {
                    size -= i.quantity
                } else {
                    size += i.quantity
                }
            }
        }
        return size
    }
    val avgEntry: Double
        get() {
            var totalShares = 0
            var totalValue: Double = 0.0
            for (i in tradeExecs) {
                if (i.side == "B" || i.side == "SS") {
                    totalShares += i.quantity
                    totalValue += (i.quantity * i.price)
                }
            }
            return if (totalShares > 0) {
                totalValue / totalShares
            } else {
                0.0 // Return 0 if no shares exist to avoid division by zero
            }
        }
    val avgExit: Double
        get() {
            var totalShares = 0
            var totalValue: Double = 0.0
            for (i in tradeExecs) {
                if (i.side == "S" || i.side == "BC") {
                    totalShares += i.quantity
                    totalValue += (i.quantity * i.price)
                }
            }
            return if (totalShares > 0) {
                totalValue / totalShares
            } else {
                0.0 // Return 0 if no shares exist to avoid division by zero
            }
        }
}
