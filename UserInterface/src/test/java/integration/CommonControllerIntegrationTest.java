package integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import userInterface.Application;


@SpringBootTest(classes= Application.class)
public class CommonControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MvcResult mvcResult;

    @BeforeEach
    public void beforeEach() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void list() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/patient/list")).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    public void add() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/patient/add")).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    public void edit() throws Exception {

        // GIVEN
        Integer userId = 1;

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/patient/edit")
                .param("id", String.valueOf(userId))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }
}