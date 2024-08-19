package quest.marketstack.tradingapp.domain.opentrades
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import quest.marketstack.tradingapp.model.OpenTrade
import quest.marketstack.tradingapp.model.TradeExec

@Primary
@Profile("!test")
@Component
class MongoOpenTradeDataSource
    @Autowired
    constructor(
        private val mongoOpenTradeRepository: MongoOpenTradeRepository,
    ) : OpenTradeDataSource {
        override fun retrieveTrades(): Collection<OpenTrade> = mongoOpenTradeRepository.findAll()

        override fun retrieveTrade(id: String): OpenTrade? = mongoOpenTradeRepository.findById(id).orElse(null)

        override fun createTrades(execList: Collection<OpenTrade>): Collection<OpenTrade> = mongoOpenTradeRepository.saveAll(execList)

        override fun deleteTrade(id: String): OpenTrade? {
            val trade = mongoOpenTradeRepository.findById(id).orElse(null)
            if (trade != null) {
                mongoOpenTradeRepository.deleteById(id)
            }

            return trade
        }

        override fun addExec(
            exec: TradeExec,
            id: String,
        ): OpenTrade? {
            val trade =
                mongoOpenTradeRepository.findById(id).orElseThrow {
                    RuntimeException("Trade not found with ID: $id")
                }

            val updatedTradeExecs = trade.tradeExecs.toMutableList()
            updatedTradeExecs.add(exec)
            trade.tradeExecs = updatedTradeExecs

            return mongoOpenTradeRepository.save(trade)
        }
    }
