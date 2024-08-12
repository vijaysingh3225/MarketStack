package quest.marketstack.TradingApp.datasource.ClosedTrades

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import quest.marketstack.TradingApp.model.Trade

@Profile("!test")
@Component
class MongoClosedTradeDataSource @Autowired constructor( private val mongoTradeExecRepository: MongoClosedTradeRepository) : ClosedTradeDataSource {
    override fun retrieveTrades(): Collection<Trade> = mongoTradeExecRepository.findAll()

    override fun retrieveTrade(id: String): Trade? = mongoTradeExecRepository.findById(id).orElse(null)

    override fun createTrade(execList: Collection<Trade>): Collection<Trade> = mongoTradeExecRepository.saveAll(execList)
}