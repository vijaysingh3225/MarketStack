package quest.marketstack.TradingApp.datasource.OpenTrades
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import quest.marketstack.TradingApp.model.Trade
import quest.marketstack.TradingApp.model.TradeExec

@Primary
@Profile("!test")
@Component
class MongoOpenTradeDataSource @Autowired constructor(
    private val mongoOpenTradeRepository: MongoOpenTradeRepository) : OpenTradeDataSource {

    override fun retrieveTrades(): Collection<Trade> {
        return mongoOpenTradeRepository.findAll()
    }

    override fun retrieveTrade(id: String): Trade? {
        return mongoOpenTradeRepository.findById(id).orElse(null)
    }

    override fun createTrades(execList: Collection<Trade>): Collection<Trade> {
        return mongoOpenTradeRepository.saveAll(execList)
    }
    override fun addExec(exec: TradeExec, id: String): Trade? {
        val trade = mongoOpenTradeRepository.findById(id).orElseThrow {
            RuntimeException("Trade not found with ID: $id")
        }

        val updatedTradeExecs = trade.tradeExecs.toMutableList()
        updatedTradeExecs.add(exec)
        trade.tradeExecs = updatedTradeExecs

        return mongoOpenTradeRepository.save(trade)
    }
}