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

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private MvcResult mvcResult;

    @Autowired
    private NoteProxy noteProxy;

    private static Integer patientId = 999999999;
    private static Date date = new Date();
    private static String commentary = "commentary";
    private static String commentaryModified = "commentaryModified";

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
                .param("patientId", String.valueOf(patientId))
                .param("commentary", commentary)).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(3)
    public void edit() throws Exception {

        // GIVEN
        String noteId = "";

        for (Note n : noteProxy.list(patientId)) {

            if (n.getPatientId().equals(patientId)) {

                if (n.getCommentary().equals(commentary)) {

                    noteId = n.getId();
                }
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
        Note note = null;

        for (Note n : noteProxy.list(patientId)) {

            if (n.getPatientId().equals(patientId)) {

                if (n.getCommentary().equals(commentary)) {

                    note = n;
                }
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/note/update")
                .param("noteId", String.valueOf(note.getId()))
                .param("patientId", String.valueOf(note.getPatientId()))
                .param("commentary", commentaryModified)).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(5)
    public void delete() throws Exception {

        // GIVEN
        String noteId = "";

        for (Note n : noteProxy.list(patientId)) {

            if (n.getPatientId().equals(patientId)) {

                if (n.getCommentary().equals(commentaryModified)) {

                    noteId = n.getId();
                }
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