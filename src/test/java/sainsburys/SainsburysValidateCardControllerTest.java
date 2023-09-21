package sainsburys;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { SainsburysValidateCardController.class, SainsburysCardValidationService.class })
@WebMvcTest(SainsburysValidateCardController.class)
public class SainsburysValidateCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String TEST_VALID_CARD_NUMBER = "712";

    private String TEST_INVALID_CARD_NUMBER = "71";

    @Test
    @Order(1)
    @DisplayName("Test 1 -  valid card number")
    public void testValidCard() throws Exception {
        this.mockMvc.perform(get("/card/number").contentType(MediaType.APPLICATION_JSON)
                                                .characterEncoding("UTF-8")
                                                .content(TEST_VALID_CARD_NUMBER))
                    .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                    .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                    .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(
                            org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().string("true"));

    }

    @Test
    @Order(2)
    @DisplayName("Test 2 -  invalid card number")
    public void testInvalidCard() throws Exception {
        this.mockMvc.perform(get("/card/number").contentType(MediaType.APPLICATION_JSON)
                                                .characterEncoding("UTF-8")
                                                .content(TEST_INVALID_CARD_NUMBER))
                    .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
                    .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isOk())
                    .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().contentType("application/json"))
                    .andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().string("false"));
    }
}
