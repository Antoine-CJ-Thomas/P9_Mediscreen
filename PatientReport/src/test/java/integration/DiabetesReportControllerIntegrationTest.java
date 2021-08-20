package integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import patientReport.Application;
import patientReport.model.DiabetesReport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes= Application.class)
public class DiabetesReportControllerIntegrationTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MvcResult mvcResult;

    @BeforeEach
    public void beforeEach() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void assessDiabetes() throws Exception {

        // GIVEN
        String gender = "M";
        List<String> commentaryList = new ArrayList<>();

        commentaryList.add("Taille");
        commentaryList.add("Poids");
        commentaryList.add("Fumeur");
        commentaryList.add("Cholestérol");
        commentaryList.add("Vertige");

        DiabetesReport diabetesReport = new DiabetesReport(gender, new Date(), commentaryList);

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/assess/diabetes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(diabetesReport))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    public void assessDiabetesById() throws Exception {

        // GIVEN
        int patientId = 1;

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/assess/diabetesById")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patientId))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    public void assessDiabetesByLastName() throws Exception {

        // GIVEN
        String lastName = "Ferguson";

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/assess/diabetesByLastName")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lastName)).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }
}