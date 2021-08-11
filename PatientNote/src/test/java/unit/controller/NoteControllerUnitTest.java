package unit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import patientNote.controller.NoteController;
import patientNote.model.Note;
import patientNote.service.NoteServiceInterface;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NoteControllerUnitTest {

    private NoteController noteController;

    private ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);
    private NoteServiceInterface noteServiceInterface = Mockito.mock(NoteServiceInterface.class);
    private HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

    @BeforeEach
    public void beforeEach() {

        noteController = new NoteController(objectMapper, noteServiceInterface);
    }

    @Test
    public void insert_status_200() throws IOException {

        //GIVEN
        Note note = Mockito.mock(Note.class);

        //WHEN
        Mockito.when(noteServiceInterface.insert(note)).thenReturn("OK");

        noteController.insert(note, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).setStatus(200);
    }

    @Test
    public void insert_status_400() throws IOException {

        //GIVEN
        Note note = Mockito.mock(Note.class);

        //WHEN
        Mockito.when(noteServiceInterface.insert(note)).thenReturn("Invalid informations");

        noteController.insert(note, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).sendError(400, "Invalid informations");
    }

    @Test
    public void select_status_200() throws IOException {

        //GIVEN
        String noteId = "noteId";
        Note note = Mockito.mock(Note.class);

        //WHEN
        Mockito.when(noteServiceInterface.select(noteId)).thenReturn(note);
        Mockito.when(objectMapper.writeValueAsString(note)).thenReturn("note");

        noteController.select(noteId, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).setStatus(200);
    }

    @Test
    public void select_status_404() throws IOException {

        //GIVEN
        String noteId = "noteId";
        Note note = Mockito.mock(Note.class);

        //WHEN
        Mockito.when(noteServiceInterface.select(noteId)).thenReturn(null);

        noteController.select(noteId, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).sendError(404, "Note with (id = " + noteId + ") could not be found");
    }

    @Test
    public void list_status_200() throws IOException {

        //GIVEN
        int patientId = 1;
        List<Note> noteList = new ArrayList<>();

        //WHEN
        Mockito.when(noteServiceInterface.list(patientId)).thenReturn(noteList);
        Mockito.when(objectMapper.writeValueAsString(noteList)).thenReturn("noteList");

        noteController.list(patientId, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).setStatus(200);
    }

    @Test
    public void list_status_404() throws IOException {

        //GIVEN
        int patientId = 1;

        //WHEN
        Mockito.when(noteServiceInterface.list(patientId)).thenReturn(null);

        noteController.list(patientId, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).sendError(404, "No patient could be found");
    }

    @Test
    public void update_status_200() throws IOException {

        //GIVEN
        String noteId = "noteId";
        Note note = Mockito.mock(Note.class);

        //WHEN
        Mockito.when(noteServiceInterface.update(noteId, note)).thenReturn("OK");

        noteController.update(noteId, note, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).setStatus(200);
    }

    @Test
    public void update_status_400() throws IOException {

        //GIVEN
        String noteId = "noteId";
        Note note = Mockito.mock(Note.class);

        //WHEN
        Mockito.when(noteServiceInterface.update(noteId, note)).thenReturn("Invalid id");

        noteController.update(noteId, note, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).sendError(400, "Invalid id");
    }

    @Test
    public void delete_status_200() throws IOException {

        //GIVEN
        String noteId = "noteId";

        //WHEN
        Mockito.when(noteServiceInterface.delete(noteId)).thenReturn("OK");

        noteController.delete(noteId, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).setStatus(200);
    }

    @Test
    public void delete_status_400() throws IOException {

        //GIVEN
        String noteId = "noteId";

        //WHEN
        Mockito.when(noteServiceInterface.delete(noteId)).thenReturn("Invalid id");

        noteController.delete(noteId, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).sendError(400, "Invalid id");
    }
}
