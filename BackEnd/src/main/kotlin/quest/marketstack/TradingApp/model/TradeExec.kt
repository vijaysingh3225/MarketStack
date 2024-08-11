package quest.marketstack.TradingApp.model
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalTime

@Document(collection = "TradeExecutions")
data class TradeExec(
        @Id
        val id: String?=null,
        val account: String,
        val tradeDate: LocalDate,
        val settlementDate: LocalDate,
        val currency: String,
        val type: Int,
        val side: String,
        val symbol: String,
        val quantity: Int,
        val price: Double,
        val execTime: LocalTime,
        val commission: Double,
        val secFee: Double,
        val taf: Double,
        val nscc: Double,
        val nasdaq: Double,
        val ecnRemove: Double,
        val ecnAdd: Double,
        val grossProceeds: Double,
        val netProceeds: Double,
        val clearingBroker: String,
        val liquidity: String,
        val note: String)



