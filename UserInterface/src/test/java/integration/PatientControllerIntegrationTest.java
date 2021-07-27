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
    private PatientProxy patientProxy;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MvcResult mvcResult;
    private static Integer id = 1;
    private static String firstName = "testFirstName";
    private static String lastName = "testLastName";
    private static String birthDate = "1990-01-01";
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
    @Order(2)
    public void update() throws Exception {

        // GIVEN
        List<Patient> patientList = patientProxy.list();

        for (Patient p : patientList) {

            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {

                id = p.getId();
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/patient/update")
                .param("id", String.valueOf(id))
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("birthDate", birthDate)
                .param("gender", gender)
                .param("address", address)
                .param("phoneNumber", "9876543210")).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(3)
    public void delete() throws Exception {

        // GIVEN
        List<Patient> patientList = patientProxy.list();

        for (Patient p : patientList) {

            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {

                id = p.getId();
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/patient/delete")
                .param("id", String.valueOf(id))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }
}