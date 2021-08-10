package unit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import patientNote.model.Note;
import patientNote.repository.NoteRepository;
import patientNote.service.NoteService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NoteServiceUnitTest {

    private NoteService noteService;

    private NoteRepository mockedNoteRepository = Mockito.mock(NoteRepository.class);

    @BeforeEach
    public void beforeEach() {

        noteService = new NoteService(mockedNoteRepository);
    }

    @Test
    public void insert_success() {

        //GIVEN
        Note note = Mockito.mock(Note.class);

        Integer patientId = 1;
        Date date = new Date(System.currentTimeMillis());
        String commentary = "commentary";

        //WHEN
        Mockito.when(note.getPatientId()).thenReturn(patientId);
        Mockito.when(note.getDate()).thenReturn(date);
        Mockito.when(note.getCommentary()).thenReturn(commentary);

        //THEN
        Assertions.assertThat(noteService.insert(note) == "OK");
    }

    @Test
    public void insert_failure() {

        //GIVEN
        Note note = Mockito.mock(Note.class);

        Integer patientId = null;

        //WHEN
        Mockito.when(note.getPatientId()).thenReturn(patientId);

        //THEN
        Assertions.assertThat(noteService.insert(note) == "Invalid informations");
    }

    @Test
    public void select_success() {

        //GIVEN
        String noteId = "noteId";
        Note note = Mockito.mock(Note.class);

        //WHEN
        Mockito.when(mockedNoteRepository.existsById(noteId)).thenReturn(true);
        Mockito.when(mockedNoteRepository.findById(noteId)).thenReturn(Optional.of(note));

        //THEN
        Assertions.assertThat(noteService.select(noteId) == note);
    }

    @Test
    public void select_failure() {

        //GIVEN
        String noteId = "noteId";

        //WHEN
        Mockito.when(mockedNoteRepository.existsById(noteId)).thenReturn(false);

        //THEN
        Assertions.assertThat(noteService.select(noteId) == null);
    }

    @Test
    public void list_success() {

        //GIVEN
        int patientId = 1;
        List<Note> noteList = new ArrayList<>();

        //WHEN
        Mockito.when(mockedNoteRepository.findByPatientId(patientId)).thenReturn(noteList);

        //THEN
        Assertions.assertThat(noteService.list(patientId) == noteList);
    }

    @Test
    public void list_failure() {

        //GIVEN
        int patientId = 1;

        //WHEN
        Mockito.when(mockedNoteRepository.findByPatientId(patientId)).thenReturn(null);

        //THEN
        Assertions.assertThat(noteService.list(patientId) == null);
    }

    @Test
    public void update_success() {

        //GIVEN
        String noteId = "noteId";
        Note note = Mockito.mock(Note.class);

        //WHEN
        Mockito.when(mockedNoteRepository.existsById(noteId)).thenReturn(true);

        //THEN
        Assertions.assertThat(noteService.update(noteId, note) == "OK");
    }

    @Test
    public void update_failure() {

        //GIVEN
        String noteId = "noteId";
        Note note = Mockito.mock(Note.class);

        //WHEN
        Mockito.when(mockedNoteRepository.existsById(noteId)).thenReturn(false);

        //THEN
        Assertions.assertThat(noteService.update(noteId, note) == "Invalid id");
    }

    @Test
    public void delete_success() {

        //GIVEN
        String noteId = "noteId";

        //WHEN
        Mockito.when(mockedNoteRepository.existsById(noteId)).thenReturn(true);

        //THEN
        Assertions.assertThat(noteService.delete(noteId) == "OK");
    }

    @Test
    public void delete_failure() {

        //GIVEN
        String noteId = "noteId";

        //WHEN
        Mockito.when(mockedNoteRepository.existsById(noteId)).thenReturn(false);

        //THEN
        Assertions.assertThat(noteService.delete(noteId) == "Invalid id");
    }
}