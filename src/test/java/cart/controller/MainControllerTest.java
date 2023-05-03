package cart.controller;

import cart.config.WebConfig;

import cart.dao.MemberDao;
import cart.dto.ProductResponse;
import cart.service.MemberService;
import cart.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(WebConfig.class)
@WebMvcTest(MainController.class)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberDao memberDao;

    @BeforeEach
    void setUp() {
        final List<ProductResponse> responses = List.of(
                new ProductResponse(1L, "무민", "moomin", 10000),
                new ProductResponse(2L, "포이", "poi", 100000));
        given(productService.findAll()).willReturn(responses);
    }

    @Test
    void showHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    void showAdmin() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    void showCart() throws Exception {
        mockMvc.perform(get("/cart"))
            .andExpect(status().isOk())
            .andExpect(view().name("cart"));
    }
}
