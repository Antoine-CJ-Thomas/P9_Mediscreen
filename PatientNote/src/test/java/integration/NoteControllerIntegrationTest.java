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
import patientNote.Application;
import patientNote.model.Note;
import patientNote.repository.NoteRepository;

import java.util.Date;
import java.util.List;

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

    private static Integer patientId = 1;
    private static Date date = new Date();
    private static String commentary = "commentary";

    @BeforeEach
    public void beforeEach() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Order(1)
    public void addNote() throws Exception {

        // GIVEN
        Note note = new Note(patientId, date, commentary);

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/note/add")
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
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/note/list")).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(3)
    public void select() throws Exception {

        // GIVEN
        int patientId = 1;
        String noteId = "";

        for (Note n : noteRepository.findByPatientId(patientId)) {

            if (n.getPatientId() == patientId && n.getDate().equals(date) && n.getCommentary().equals(commentary)) {

                noteId = n.getId();
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/note/select")
                .param("id", noteId)).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(4)
    public void update() throws Exception {

        // GIVEN
        int patientId = 1;
        String noteId = "";
        Note note = new Note(patientId, date, "commentaryModified");

        for (Note n : noteRepository.findByPatientId(patientId)) {

            if (n.getPatientId() == patientId && n.getDate() == date && n.getCommentary().equals(commentary)) {

                noteId = n.getId();
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/note/update")
                .param("id", String.valueOf(noteId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(note))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }

    @Test
    @Order(5)
    public void delete() throws Exception {

        // GIVEN
        int patientId = 1;
        String noteId = "";

        for (Note n : noteRepository.findByPatientId(patientId)) {

            if (n.getPatientId() == patientId && n.getDate() == date && n.getCommentary().equals(commentary)) {

                noteId = n.getId();
            }
        }

        // WHEN
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/note/delete")
                .param("id", String.valueOf(noteId))).andReturn();

        // THEN
        Assertions.assertThat(mvcResult.getResponse().getStatus() == 200);
    }
}
