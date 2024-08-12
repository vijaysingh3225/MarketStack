package quest.marketstack.TradingApp.datasource.ClosedTrades

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import quest.marketstack.TradingApp.model.ClosedTrade
import quest.marketstack.TradingApp.model.OpenTrade

@Profile("!test")
@Component
class MongoClosedTradeDataSource @Autowired constructor( private val mongoTradeExecRepository: MongoClosedTradeRepository) : ClosedTradeDataSource {
    override fun retrieveTrades(): Collection<ClosedTrade> = mongoTradeExecRepository.findAll()

    override fun retrieveTrade(id: String): ClosedTrade? = mongoTradeExecRepository.findById(id).orElse(null)

    override fun createTrade(execList: Collection<ClosedTrade>): Collection<ClosedTrade> = mongoTradeExecRepository.saveAll(execList)
}