package unit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import patientNote.controller.NoteController;
import patientNote.model.Note;
import patientNote.service.NoteServiceInterface;

public class NoteControllerUnitTest {

    private NoteController patientController;

    private NoteServiceInterface noteServiceInterface = Mockito.mock(NoteServiceInterface.class);

    @BeforeEach
    public void beforeEach() {

        patientController = new NoteController(noteServiceInterface);
    }

    @Test
    public void insert() {

        //GIVEN
        Note note = Mockito.mock(Note.class);

        //WHEN
        patientController.insert(note);

        //THEN
        Mockito.verify(noteServiceInterface, Mockito.times(1)).insert(note);
    }

    @Test
    public void select() throws JsonProcessingException {

        //GIVEN
        String noteId = "noteId";

        //WHEN
        patientController.select(noteId);

        //THEN
        Mockito.verify(noteServiceInterface, Mockito.times(1)).select(noteId);
    }

    @Test
    public void list() throws JsonProcessingException {

        //GIVEN
        int patientId = 1;

        //WHEN
        patientController.list(patientId);

        //THEN
        Mockito.verify(noteServiceInterface, Mockito.times(1)).list(patientId);
    }

    @Test
    public void update() {

        //GIVEN
        String noteId = "noteId";
        Note note = Mockito.mock(Note.class);

        //WHEN
        patientController.update(noteId, note);

        //THEN
        Mockito.verify(noteServiceInterface, Mockito.times(1)).update(noteId, note);
    }

    @Test
    public void delete() {

        //GIVEN
        String noteId = "noteId";

        //WHEN
        patientController.delete(noteId);

        //THEN
        Mockito.verify(noteServiceInterface, Mockito.times(1)).delete(noteId);
    }
}
