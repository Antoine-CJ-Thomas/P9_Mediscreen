package unit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import patientReport.model.Note;
import patientReport.proxy.NoteProxy;
import patientReport.service.NoteService;

import java.util.List;

public class NoteServiceUnitTest {

    private NoteService noteService;

    private NoteProxy mockedNoteProxy = Mockito.mock(NoteProxy.class);

    @BeforeEach
    public void beforeEach() {

        noteService = new NoteService(mockedNoteProxy);
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
}
