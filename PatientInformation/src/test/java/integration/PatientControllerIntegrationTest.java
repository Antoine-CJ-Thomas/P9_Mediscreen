package integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import patientInformation.Application;
import patientInformation.model.Patient;

import java.sql.Date;
import java.util.List;

@SpringBootTest(classes= Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientControllerIntegrationTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MvcResult mvcResult;

    private static Integer id;
    private static String firstName = "testFirstName";
    private static String lastName = "testLastName";
    private static Date birthDate = new Date(System.currentTimeMillis());
    private static String gender = "M";
    private static String address = "testAddress";
    private static String phoneNumber = "0123456789";

    @BeforeEach
    public void beforeEach() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void insert() throws Exception {

        // GIVEN
        Patient patient = new Patient(firstName, lastName, birthDate, gender, address, phoneNumber);

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/patient/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(2)
    public void list() throws Exception {

        // GIVEN
        List<Patient> patientList;

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/patient/list")).andReturn();

        // THEN
        patientList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Patient>>(){});

        for (Patient p : patientList) {

            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {

                id = p.getId();
            }
        }

        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(3)
    public void select() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/patient/select")
                .param("id", String.valueOf(id))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(4)
    public void update() throws Exception {

        // GIVEN
        Patient patient = new Patient(firstName, lastName, birthDate, gender, address, "9876543210");

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/patient/update")
                .param("id", String.valueOf(id))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(5)
    public void delete() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/patient/delete")
                .param("id", String.valueOf(id))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }
}