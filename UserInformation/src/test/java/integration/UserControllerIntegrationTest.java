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
import userInformation.Application;
import userInformation.model.User;

import java.sql.Date;
import java.util.List;

@SpringBootTest(classes= Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerIntegrationTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MvcResult mvcResult;

    private static Integer userId;
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
    public void insertUser() throws Exception {

        // GIVEN
        User user = new User(firstName, lastName, birthDate, gender, address, phoneNumber);

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/insertUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(2)
    public void selectUserList() throws Exception {

        // GIVEN
        List<User> userList;

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/selectUserList")).andReturn();

        // THEN
        userList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<User>>(){});

        for (User u : userList) {

            if (u.getFirstName().equals(firstName) && u.getLastName().equals(lastName)) {

                userId = u.getUserId();
            }
        }

        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(3)
    public void selectUser() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/selectUser")
                .param("id", String.valueOf(userId))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(4)
    public void updateUser() throws Exception {

        // GIVEN
        User user = new User(firstName, lastName, birthDate, gender, address, "9876543210");

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/updateUser")
                .param("id", String.valueOf(userId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(5)
    public void deleteUser() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/deleteUser")
                .param("id", String.valueOf(userId))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }
}