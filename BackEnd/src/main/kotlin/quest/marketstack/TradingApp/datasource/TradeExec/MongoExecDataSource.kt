package quest.marketstack.TradingApp.datasource.TradeExec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import quest.marketstack.TradingApp.model.Trade
import quest.marketstack.TradingApp.model.TradeExec

@Primary
@Profile("!test")
@Component
class MongoExecDataSource @Autowired constructor(
    private val mongoTradeExecRepository: MongoTradeExecRepository) : TradeExecDataSource {

    override fun retrieveTrades(): Collection<Trade> {
        return mongoTradeExecRepository.findAll()
    }

    override fun retrieveTrade(id: String): Trade? {
        return mongoTradeExecRepository.findById(id).orElse(null)
    }

    override fun createTrades(execList: Collection<Trade>): Collection<Trade> {
        return mongoTradeExecRepository.saveAll(execList)
    }
    override fun addExec(exec: TradeExec, id: String): Trade {
        // Fetch the Trade object by its ID
        val trade = mongoTradeExecRepository.findById(id).orElseThrow {
            RuntimeException("Trade not found with ID: $id")
        }

        // Add the new TradeExec to the tradeExecs collection
        val updatedTradeExecs = trade.tradeExecs.toMutableList()
        updatedTradeExecs.add(exec)
        trade.tradeExecs = updatedTradeExecs

        // Save the updated Trade object
        return mongoTradeExecRepository.save(trade)
    }
}