package quest.marketstack.TradingApp.datasource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import quest.marketstack.TradingApp.datasource.TradeDataSource
import quest.marketstack.TradingApp.model.TradeExec

@Primary
@Profile("!test")
@Component
class MongoTradeDataSource @Autowired constructor(
    private val tradeExecRepository: TradeExecRepository
) : TradeDataSource {

    override fun retrieveTradeExecs(): Collection<TradeExec> {
        return tradeExecRepository.findAll()
    }

    override fun retrieveTradeExec(id: String): TradeExec? {
        return tradeExecRepository.findById(id).orElse(null)
    }

    override fun createTradeExec(exec: TradeExec): TradeExec {
        return tradeExecRepository.save(exec)
    }
}