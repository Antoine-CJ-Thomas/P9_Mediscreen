package integration;

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
import patientNote.Application;
import patientNote.model.Note;
import patientNote.repository.NoteRepository;

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
    private NoteRepository noteRepository;

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
    public void insert() throws Exception {

        // GIVEN
        Note note = new Note(patientId, date, commentary);

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/note/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(note))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(2)
    public void list() throws Exception {

        // GIVEN

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/note/list")
                .param("patientId", String.valueOf(patientId))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(3)
    public void select() throws Exception {

        // GIVEN
        Note note = null;

        for (Note n : noteRepository.findByPatientId(patientId)) {

            if (n.getPatientId().equals(patientId)) {

                if (n.getDate().equals(date)) {

                    if (n.getCommentary().equals(commentary)) {

                        note = n;
                    }
                }
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/note/select")
                .param("id", note.getId())).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(4)
    public void update() throws Exception {

        // GIVEN
        Note note = null;

        for (Note n : noteRepository.findByPatientId(patientId)) {

            if (n.getPatientId().equals(patientId)) {

                if (n.getDate().equals(date)) {

                    if (n.getCommentary().equals(commentary)) {

                        note = n;
                    }
                }
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/note/update")
                .param("id", note.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Note(patientId, date, commentaryModified)))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(5)
    public void delete() throws Exception {

        // GIVEN
        Note note = null;

        for (Note n : noteRepository.findByPatientId(patientId)) {

            if (n.getPatientId().equals(patientId)) {

                if (n.getDate().equals(date)) {

                    if (n.getCommentary().equals(commentaryModified)) {

                        note = n;
                    }
                }
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/note/delete")
                .param("id", note.getId())).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }
}