package quest.marketstack.TradingApp.datasource.TradeExec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import quest.marketstack.TradingApp.model.TradeExec

@Primary
@Profile("!test")
@Component
class MongoExecutionDataSource @Autowired constructor(
    private val tradeExecRepository: TradeExecRepository
) : ExecutionDataSource {

    override fun retrieveTradeExecs(): Collection<TradeExec> {
        return tradeExecRepository.findAll()
    }

    override fun retrieveTradeExec(id: String): TradeExec? {
        return tradeExecRepository.findById(id).orElse(null)
    }

    override fun createTradeExecs(execList: List<TradeExec>): List<TradeExec> {
        return tradeExecRepository.saveAll(execList)
    }
}