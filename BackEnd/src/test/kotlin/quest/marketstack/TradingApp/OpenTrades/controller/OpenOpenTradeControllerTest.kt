package quest.marketstack.TradingApp.OpenTrades.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import quest.marketstack.TradingApp.model.TradeExec
import java.time.LocalDate
import java.time.LocalTime


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class OpenOpenTradeControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    val baseUrl = "/api/openTrades"
    val closedURL = "api/closedTrades"

    @Nested
    @DisplayName("GET /api/tradeExecs")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetOpenTradeExecs {
        @Test
        fun `should return all Open Trades`() {
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }
        }
    }

    @Nested
    @DisplayName("GET /api/tradeExec/{Id}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetOpenTradeExec {

        @Test
        fun `should return Not Found if the id does not exist`() {
            val id = "232"

            mockMvc.get("$baseUrl/$id")
                .andDo { print() }
                .andExpect { status { isOk() } }
        }

        @Test
        fun `should return the trade with the given id`() {
            val id = "123";

            mockMvc.get("$baseUrl/$id")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                }
        }
    }

    @Nested
    @DisplayName("POST /api/tradeExecs")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostOpenTradeExec {
        @Test
        @DirtiesContext
        fun `should add a new trade execution in a new trade object`() {

            val group:List<TradeExec> = listOf(TradeExec(
                id = "142",account = "8923", tradeDate = LocalDate.of(2021, 7, 15), settlementDate = LocalDate.of(2022, 7, 20), currency = "EUR", type = 2,
                side = "B", symbol = "GOOGL", quantity = 150, price = 2728.95, execTime = LocalTime.of(14, 45, 30), commission = 5.75,
                secFee = 0.02, taf = 0.03, nscc = 0.01, nasdaq = 0.02, ecnRemove = 0.01, ecnAdd = 0.02, grossProceeds = 409342.50, netProceeds = 409326.18, clearingBroker = "BKR1",
                liquidity = "High", note = "Executed trade with adjusted settings"))

            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(group)
            }
                performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                }
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    jsonPath("$", hasSize<Any>(2))
                    jsonPath("$[1].tradeExecs", hasSize<Any>(1))
                }
        }
        @Test
        @DirtiesContext
        fun `should add trade execution to existing trade object`() {
            val group:List<TradeExec> = listOf(TradeExec(
                id = "133",account = "8923", tradeDate = LocalDate.of(2021, 7, 15), settlementDate = LocalDate.of(2022, 7, 20), currency = "EUR", type = 2,
                side = "BC", symbol = "UROY", quantity = 150, price = 2728.95, execTime = LocalTime.of(14, 45, 30), commission = 5.75,
                secFee = 0.02, taf = 0.03, nscc = 0.01, nasdaq = 0.02, ecnRemove = 0.01, ecnAdd = 0.02, grossProceeds = 409342.50, netProceeds = 409326.18, clearingBroker = "BKR1",
                liquidity = "High", note = "Executed trade with adjusted settings"))

            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(group)
            }

            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                }
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    jsonPath("$", hasSize<Any>(2))
                    jsonPath("$[0].tradeExecs", hasSize<Any>(3))
                }
        }
        @Test
        @DirtiesContext
        fun `should reject opening trades with sell execs and buy cover execs`() {
            val group:List<TradeExec> = listOf(TradeExec(
                    id = "275",account = "8923", tradeDate = LocalDate.of(2021, 7, 15), settlementDate = LocalDate.of(2022, 7, 20), currency = "EUR", type = 2,
                side = "BC", symbol = "MSFT", quantity = 150, price = 2728.95, execTime = LocalTime.of(14, 45, 30), commission = 5.75,
                secFee = 0.02, taf = 0.03, nscc = 0.01, nasdaq = 0.02, ecnRemove = 0.01, ecnAdd = 0.02, grossProceeds = 409342.50, netProceeds = 409326.18, clearingBroker = "BKR1",
                liquidity = "High", note = "Executed trade with adjusted settings"),TradeExec(
                id = "063",account = "8923", tradeDate = LocalDate.of(2021, 7, 15), settlementDate = LocalDate.of(2022, 7, 20), currency = "EUR", type = 2,
                side = "S", symbol = "MSFT", quantity = 150, price = 2728.95, execTime = LocalTime.of(14, 45, 30), commission = 5.75,
                secFee = 0.02, taf = 0.03, nscc = 0.01, nasdaq = 0.02, ecnRemove = 0.01, ecnAdd = 0.02, grossProceeds = 409342.50, netProceeds = 409326.18, clearingBroker = "BKR1",
                liquidity = "High", note = "Executed trade with adjusted settings"))

            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(group)
            }

            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                }
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    jsonPath("$", hasSize<Any>(1))
                    jsonPath("$[0].tradeExecs", hasSize<Any>(2))
                }

        }
        @Test
        @DirtiesContext
        fun `should close posted trade and delete from open trade database`() {
            val group:List<TradeExec> = listOf(TradeExec(
                id = "275",account = "8923", tradeDate = LocalDate.of(2021, 7, 15), settlementDate = LocalDate.of(2022, 7, 20), currency = "EUR", type = 2,
                side = "B", symbol = "RDBX", quantity = 150, price = 2728.95, execTime = LocalTime.of(14, 45, 30), commission = 5.75,
                secFee = 0.02, taf = 0.03, nscc = 0.01, nasdaq = 0.02, ecnRemove = 0.01, ecnAdd = 0.02, grossProceeds = 409342.50, netProceeds = 409326.18, clearingBroker = "BKR1",
                liquidity = "High", note = "Executed trade with adjusted settings"),TradeExec(
                id = "063",account = "8923", tradeDate = LocalDate.of(2021, 7, 15), settlementDate = LocalDate.of(2022, 7, 20), currency = "EUR", type = 2,
                side = "S", symbol = "RDBX", quantity = 150, price = 2729.95, execTime = LocalTime.of(14, 45, 30), commission = 5.75,
                secFee = 0.02, taf = 0.03, nscc = 0.01, nasdaq = 0.02, ecnRemove = 0.01, ecnAdd = 0.02, grossProceeds = 409342.50, netProceeds = 409326.18, clearingBroker = "BKR1",
                liquidity = "High", note = "Executed trade with adjusted settings"))
            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(group)
            }

            performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                }
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    jsonPath("$", hasSize<Any>(1))
                }
            mockMvc.get(closedURL)
                .andDo { print() }
        }
    }
}