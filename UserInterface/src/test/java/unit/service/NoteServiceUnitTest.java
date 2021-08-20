package unit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import userInterface.model.Note;
import userInterface.model.Patient;
import userInterface.proxy.NoteProxy;
import userInterface.service.NoteService;

import java.util.List;

public class NoteServiceUnitTest {

    private NoteService noteService;

    private NoteProxy mockedNoteProxy = Mockito.mock(NoteProxy.class);

    @BeforeEach
    public void beforeEach() {

        noteService = new NoteService(mockedNoteProxy);
    }

    @Test
    public void insert() {

        //GIVEN
        Note note = Mockito.mock(Note.class);

        //WHEN
        noteService.insert(note);

        //THEN
        Mockito.verify(mockedNoteProxy, Mockito.times(1)).insert(note);
    }

    @Test
    public void select() {

        //GIVEN
        String noteId = "noteId";
        Note note = Mockito.mock(Note.class);

        //WHEN
        Mockito.when(mockedNoteProxy.select(noteId)).thenReturn(note);

        //THEN
        Assertions.assertThat(noteService.select(noteId) == note);
    }

    @Test
    public void list() {

        //GIVEN
        int patientId = 1;
        List<Note> noteList = Mockito.mock(List.class);

        //WHEN
        Mockito.when(mockedNoteProxy.list(patientId)).thenReturn(noteList);

        //THEN
        Assertions.assertThat(noteService.list(patientId) == noteList);
    }

    @Test
    public void update() {

        //GIVEN
        String noteId = "noteId";
        Note note = Mockito.mock(Note.class);

        //WHEN
        noteService.update(noteId, note);

        //THEN
        Mockito.verify(mockedNoteProxy, Mockito.times(1)).update(noteId, note);
    }

    @Test
    public void delete() {

        //GIVEN
        String noteId = "noteId";

        //WHEN
        noteService.delete(noteId);

        //THEN
        Mockito.verify(mockedNoteProxy, Mockito.times(1)).delete(noteId);
    }
}
