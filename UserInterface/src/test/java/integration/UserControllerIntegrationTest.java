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
import userInterface.model.User;
import userInterface.proxy.UserProxy;

import java.text.SimpleDateFormat;
import java.util.List;


@SpringBootTest(classes= Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerIntegrationTest {

    @Autowired
    UserProxy userProxy;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MvcResult mvcResult;
    private static Integer userId = 1;
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
    public void addUser() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/addUser")).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(2)
    public void insertUser() throws Exception {

        // GIVEN
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/insertUser")
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
    public void getUserList() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/userList")).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(4)
    public void editUser() throws Exception {

        // GIVEN
        List<User> userList = userProxy.selectUserList();

        for (User u : userList) {

            if (u.getFirstName().equals(firstName) && u.getLastName().equals(lastName)) {

                userId = u.getUserId();
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/editUser")
                .param("id", String.valueOf(userId))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(5)
    public void updateUser() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/updateUser")
                .param("id", String.valueOf(userId))
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
    @Order(6)
    public void deleteUser() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/deleteUser")
                .param("id", String.valueOf(userId))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }
}
