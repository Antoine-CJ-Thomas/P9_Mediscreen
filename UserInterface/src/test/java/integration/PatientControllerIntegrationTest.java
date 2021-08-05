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
import userInterface.model.Patient;
import userInterface.proxy.PatientProxy;

import java.text.SimpleDateFormat;
import java.util.List;


@SpringBootTest(classes= Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MvcResult mvcResult;

    @Autowired
    private PatientProxy patientProxy;

    private static String firstName = "testFirstName";
    private static String lastName = "testLastName";
    private static String birthDate = "1990-01-01";
    private static String gender = "M";
    private static String address = "testAddress";
    private static String phoneNumber = "0123456789";
    private static String phoneNumberModified = "0123456789";

    @BeforeEach
    public void beforeEach() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void add() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/patient/add")).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(2)
    public void insert() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/patient/insert")
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("birthDate", birthDate)
                .param("gender", gender)
                .param("address", address)
                .param("phoneNumber", phoneNumber)).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(3)
    public void edit() throws Exception {

        // GIVEN
        Patient patient = null;

        for (Patient p : patientProxy.list()) {

            if (p.getFirstName().equals(firstName)) {

                if (p.getLastName().equals(lastName)) {

                    patient = p;
                }
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/patient/edit")
                .param("patientId", String.valueOf(patient.getId()))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(4)
    public void update() throws Exception {

        // GIVEN
        Patient patient = null;

        for (Patient p : patientProxy.list()) {

            if (p.getFirstName().equals(firstName)) {

                if (p.getLastName().equals(lastName)) {

                    patient = p;
                }
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/patient/update")
                .param("patientId", String.valueOf(patient.getId()))
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("birthDate", birthDate)
                .param("gender", gender)
                .param("address", address)
                .param("phoneNumber", phoneNumberModified)).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(5)
    public void delete() throws Exception {

        // GIVEN
        Patient patient = null;

        for (Patient p : patientProxy.list()) {

            if (p.getFirstName().equals(firstName)) {

                if (p.getLastName().equals(lastName)) {

                    patient = p;
                }
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/patient/delete")
                .param("patientId", String.valueOf(patient.getId()))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }
}