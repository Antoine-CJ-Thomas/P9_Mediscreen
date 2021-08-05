package userInterface.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import userInterface.service.NoteService;
import userInterface.service.NoteServiceInterface;

import javax.validation.Valid;
import java.util.Date;

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

    @GetMapping("/add")
    public String add(@RequestParam int patientId, Note note, Model model) {
        logger.info("add(" + patientId + "," + note + "," + model + ")");

        model.addAttribute("patientId", patientId);
        model.addAttribute("note", note);

        return "note_add.html";
    }

    @PostMapping("/insert")
    public String insert(@Valid Note note, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        logger.info("insert(" + note + "," + bindingResult + "," + redirectAttributes + "," + model + ")");

        if (!bindingResult.hasErrors()) {

            note.setDate(new Date());

            noteServiceInterface.insert(note);

            redirectAttributes.addAttribute("patientId", note.getPatientId());

            return ("redirect:/patient/open");
        }

        else {

            model.addAttribute("note", note);

            return "note_add.html";
        }
    }

    @GetMapping("/edit")
    public String editNote(@RequestParam int patientId, @RequestParam String noteId, Model model) {
        logger.info("editNote(" + patientId + "," + noteId + ","  + model + ")");

        model.addAttribute("patientId", patientId);
        model.addAttribute("note", noteServiceInterface.select(noteId));

        return "note_edit.html";
    }

    @PostMapping("/update")
    public String update(@RequestParam String noteId, @Valid Note note, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        logger.info("update(" + noteId + "," + note + "," + bindingResult + "," + model + ")");

        if (!bindingResult.hasErrors()) {

            noteServiceInterface.update(noteId, note);

            redirectAttributes.addAttribute("patientId", note.getPatientId());

            return ("redirect:/patient/open");
        }

        else {

            return "note_edit.html";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int patientId, @RequestParam String noteId, RedirectAttributes redirectAttributes, Model model) {
        logger.info("delete(" + patientId + "," + noteId + "," + model + ")");

        noteServiceInterface.delete(noteId);

        redirectAttributes.addAttribute("patientId", patientId);

        return ("redirect:/patient/open");
    }
}
