package integration;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import userInterface.model.Note;
import userInterface.proxy.NoteProxy;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest(classes= Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NoteControllerIntegrationTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MvcResult mvcResult;

    @Autowired
    private NoteProxy noteProxy;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static String patientId = "1";
    private static String dateString = simpleDateFormat.format(new Date());
    private static String commentary = "commentary";

    @BeforeEach
    public void beforeEach() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void add() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/note/add")
                .param("patientId", String.valueOf(patientId))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(2)
    public void insert() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/note/insert")
                .param("patientId", patientId)
                .param("date", dateString)
                .param("commentary", commentary)).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(3)
    public void edit() throws Exception {

        // GIVEN
        int patientId = 1;
        String noteId = "";

        for (Note n : noteProxy.list(patientId)) {

            if (n.getPatientId() == patientId && n.getCommentary().equals(commentary)) {

                noteId = n.getId();
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/note/edit")
                .param("patientId", String.valueOf(patientId))
                .param("noteId", noteId)).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(4)
    public void update() throws Exception {

        // GIVEN
        int patientId = 1;
        String noteId = "";

        for (Note n : noteProxy.list(patientId)) {

            if (n.getPatientId() == patientId && n.getCommentary().equals(commentary)) {

                noteId = n.getId();
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/note/update")
                .param("noteId", noteId)
                .param("patientId", String.valueOf(patientId))
                .param("date", dateString)
                .param("commentary", "commentaryModified")).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(5)
    public void delete() throws Exception {

        // GIVEN
        int patientId = 1;
        String noteId = "";

        for (Note n : noteProxy.list(patientId)) {

            if (n.getPatientId() == patientId && n.getCommentary().equals(commentary)) {

                noteId = n.getId();
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/note/delete")
                .param("patientId", String.valueOf(patientId))
                .param("noteId", noteId)).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }
}
