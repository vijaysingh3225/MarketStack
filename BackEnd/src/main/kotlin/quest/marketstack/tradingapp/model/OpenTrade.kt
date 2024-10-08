package quest.marketstack.tradingapp.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "\${api.open-trade.collection}")
data class OpenTrade(
    @Id
    val id: String? = null,
    var tradeExecs: MutableCollection<TradeExec> = mutableListOf(),
    val shortLong: Boolean,
) {
    val ProfitLoss: Double get() {
        var openAvg = 0.0
        var closeAvg = 0.0
        var openExecs = 0
        var closeExecs = 0
        var openShares = 0
        var closedShares = 0
        var profitLoss: Double

        if (shortLong) {
            for (i in tradeExecs) {
                if (i.side == "B") {
                    openExecs++
                    openAvg += (i.price * i.quantity)
                    openShares += i.quantity
                } else {
                    closeExecs++
                    openAvg += (i.price * i.quantity)
                    closedShares += i.quantity
                }
            }
        } else {
            for (i in tradeExecs) {
                if (i.side == "SS") {
                    openExecs++
                    openAvg += (i.price * i.quantity)
                    openShares += i.quantity
                } else {
                    closeExecs++
                    openAvg += (i.price * i.quantity)
                    closedShares += i.quantity
                }
            }
        }
        openAvg /= openExecs
        closeAvg /= closeExecs
        profitLoss =
            if (shortLong) {
                (openShares * openAvg) - (closedShares * closeAvg)
            } else {
                (closedShares * closeAvg) - (openShares * openAvg)
            }

        return profitLoss
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

    fun covertToClosed(openTrade: OpenTrade): ClosedTrade {
        val newClosedTrade: ClosedTrade = ClosedTrade(openTrade.id, openTrade.tradeExecs, openTrade.shortLong)
        return newClosedTrade
    }
}
