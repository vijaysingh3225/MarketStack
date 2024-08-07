package quest.marketstack.TradingApp.controller

import com.fasterxml.jackson.databind.ObjectMapper
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
class TradeControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    val baseUrl = "/api/tradeExecs"

    @Nested
    @DisplayName("GET /api/tradeExecs")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetTradeExecs {
        @Test
        fun `should return all TradeExecs`() {
            mockMvc.get(baseUrl)
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].account") { value("2313") }
                }
        }
    }

    @Nested
    @DisplayName("GET /api/tradeExec/{Id}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetTradeExec {


        @Test
        fun `should return Not Found if the id does not exist`() {
            val id = "323"

            mockMvc.get("$baseUrl/$id")
                .andDo { print() }
                .andExpect { status { isOk() } }
        }

        @Test
        fun `should return the trade with the given id`() {
            val id = "234";

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
    inner class PostTradeExec {
        @Test
        fun `should add a new trade execution`() {
            val newExec = TradeExec(
                id = "4567",account = "8923", tradeDate = LocalDate.of(2022, 7, 15), settlementDate = LocalDate.of(2022, 7, 20), currency = "EUR", type = 2,
                side = "Buy", symbol = "GOOGL", quantity = 150, price = 2728.95, execTime = LocalTime.of(14, 45, 30), commission = 5.75,
                secFee = 0.02, taf = 0.03, nscc = 0.01, nasdaq = 0.02, ecnRemove = 0.01, ecnAdd = 0.02, grossProceeds = 409342.50, netProceeds = 409326.18, clearingBroker = "BKR1",
                liquidity = "High", note = "Executed trade with adjusted settings")

            val performPost = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newExec)
            }
                performPost
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                }

        }


    }

}