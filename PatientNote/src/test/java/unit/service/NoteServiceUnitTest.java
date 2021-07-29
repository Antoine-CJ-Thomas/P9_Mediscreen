package unit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import patientNote.model.Note;
import patientNote.repository.NoteRepository;
import patientNote.service.NoteService;

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
    public void insert() {

        //GIVEN
        Note note = Mockito.mock(Note.class);

        //WHEN
        noteService.insert(note);

        //THEN
        Mockito.verify(mockedNoteRepository, Mockito.times(1)).save(note);
    }

    @Test
    public void select() {

        //GIVEN
        String noteId = "noteId";
        Note note = Mockito.mock(Note.class);

        //WHEN
        Mockito.when(mockedNoteRepository.findById(noteId)).thenReturn(Optional.of(note));

        //THEN
        Assertions.assertThat(noteService.select(noteId) == note);
    }

    @Test
    public void list() {

        //GIVEN
        int patientId = 1;
        Note note = Mockito.mock(Note.class);
        List<Note> noteList = Arrays.asList(note);

        //WHEN
        Mockito.when(mockedNoteRepository.findByPatientId(patientId)).thenReturn(noteList);

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
        Mockito.verify(mockedNoteRepository, Mockito.times(1)).save(note);
    }

    @Test
    public void delete() {

        //GIVEN
        String noteId = "noteId";

        //WHEN
        noteService.delete(noteId);

        //THEN
        Mockito.verify(mockedNoteRepository, Mockito.times(1)).deleteById(noteId);
    }
}
