package unit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import userInterface.controller.NoteController;
import userInterface.model.Note;
import userInterface.service.NoteServiceInterface;

public class NoteControllerUnitTest {

    private NoteController noteController;

    private NoteServiceInterface noteServiceInterface = Mockito.mock(NoteServiceInterface.class);

    @BeforeEach
    public void beforeEach() {

        noteController = new NoteController(noteServiceInterface);
    }

    @Test
    public void addNote() {

        //GIVEN
        int patientNote = 1;
        Note note = Mockito.mock(Note.class);
        Model model = Mockito.mock(Model.class);

        //WHEN
        noteController.addNote(patientNote, note, model);

        //THEN
        Mockito.verify(model, Mockito.times(1)).addAttribute("note", note);
    }

    @Test
    public void insert() {

        //GIVEN
        Note note = Mockito.mock(Note.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        RedirectAttributes redirectAttributes = Mockito.mock(RedirectAttributes.class);
        Model model = Mockito.mock(Model.class);

        //WHEN
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        noteController.insert(note, bindingResult, redirectAttributes, model);

        //THEN
        Mockito.verify(noteServiceInterface, Mockito.times(1)).insert(note);
    }

    @Test
    public void editNote() {

        //GIVEN
        int patientNote = 1;
        String noteId = "noteId";
        Model model = Mockito.mock(Model.class);

        //WHEN
        noteController.editNote(patientNote, noteId, model);

        //THEN
        Mockito.verify(noteServiceInterface, Mockito.times(1)).select(noteId);
    }

    @Test
    public void update() {

        //GIVEN
        String noteId = "noteId";
        Note note = Mockito.mock(Note.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        RedirectAttributes redirectAttributes = Mockito.mock(RedirectAttributes.class);
        Model model = Mockito.mock(Model.class);

        //WHEN
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        noteController.update(noteId, note, bindingResult, redirectAttributes, model);

        //THEN
        Mockito.verify(noteServiceInterface, Mockito.times(1)).update(noteId, note);
    }

    @Test
    public void delete() {

        //GIVEN
        int patientNote = 1;
        String noteId = "noteId";
        RedirectAttributes redirectAttributes = Mockito.mock(RedirectAttributes.class);
        Model model = Mockito.mock(Model.class);

        //WHEN
        noteController.delete(patientNote, noteId, redirectAttributes, model);

        //THEN
        Mockito.verify(noteServiceInterface, Mockito.times(1)).delete(noteId);
    }
}
