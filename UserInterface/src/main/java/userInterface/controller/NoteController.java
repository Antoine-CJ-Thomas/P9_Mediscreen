package userInterface.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import userInterface.model.Note;
import userInterface.model.Patient;
import userInterface.service.NoteService;
import userInterface.service.NoteServiceInterface;
import userInterface.service.PatientService;
import userInterface.service.PatientServiceInterface;

import javax.validation.Valid;

@Controller
@RequestMapping("/note")
public class NoteController {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private NoteServiceInterface noteServiceInterface;

    public NoteController() {
        logger.info("NoteController()");

        noteServiceInterface = new NoteService();
    }

    public NoteController(NoteServiceInterface noteServiceInterface) {
        logger.info("NoteController(" + noteServiceInterface + ")");

        this.noteServiceInterface = noteServiceInterface;
    }

    @PostMapping("/update")
    public String update(@RequestParam String id, @Valid Note note, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        logger.info("update(" + id + "," + note + "," + bindingResult + "," + model + ")");

        if (!bindingResult.hasErrors()) {

            noteServiceInterface.update(id, note);

            redirectAttributes.addAttribute("id", note.getPatientId());

            return ("redirect:/patient/open");
        }

        else {

            return "/note_edit.html";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int patientId, @RequestParam String noteId, RedirectAttributes redirectAttributes, Model model) {
        logger.info("delete(" + patientId + "," + noteId + "," + model + ")");

        noteServiceInterface.delete(noteId);

        redirectAttributes.addAttribute("id", patientId);

        return ("redirect:/patient/open");
    }
}
