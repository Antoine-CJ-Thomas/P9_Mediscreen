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

/**
 * This class is used to intercept requests related to patient note
 */
@Controller
@RequestMapping("/note")
public class NoteController {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private NoteServiceInterface noteServiceInterface;

    /**
     * Creates a new NoteController
     */
    public NoteController() {
        logger.info("NoteController()");

        noteServiceInterface = new NoteService();
    }

    /**
     * Creates a new NoteController with the specified NoteServiceInterface
     * @param noteServiceInterface : service that this controller will use
     */
    public NoteController(NoteServiceInterface noteServiceInterface) {
        logger.info("NoteController(" + noteServiceInterface + ")");

        this.noteServiceInterface = noteServiceInterface;
    }

    /**
     * Get the html page that allow to add a note to a patient
     * @param patientId : id of the patient to add a note to
     * @param note : note default data
     * @param model : holder for model attribute
     * @return The html page that allow add a note to the patient
     */
    @GetMapping("/add")
    public String add(@RequestParam int patientId, Note note, Model model) {
        logger.info("add(" + patientId + "," + note + "," + model + ")");

        model.addAttribute("patientId", patientId);
        model.addAttribute("note", note);

        return "note_add.html";
    }

    /**
     * Perform a insert of a note to a patient
     * @param note : note to add to a patient
     * @param bindingResult : holder for dataBinder result
     * @param redirectAttributes : holder for redirect attribute
     * @param model : holder for model attribute
     * @return The html page that allow to consult the medical record of the patient or the html page that allow add
     * a note to the patient in case of binding error
     */
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

    /**
     * Get the html page that allow to edit a note to a patient
     * @param patientId : id of the patient to edit a note to
     * @param noteId : id of the note to edit
     * @param model : holder for model attribute
     * @return The html page that allow edit a note to the patient
     */
    @GetMapping("/edit")
    public String editNote(@RequestParam int patientId, @RequestParam String noteId, Model model) {
        logger.info("editNote(" + patientId + "," + noteId + ","  + model + ")");

        model.addAttribute("patientId", patientId);
        model.addAttribute("note", noteServiceInterface.select(noteId));

        return "note_edit.html";
    }

    /**
     * Perform a update of a note to a patient
     * @param noteId : id of the note to update
     * @param note : note to add to a patient
     * @param bindingResult : holder for dataBinder result
     * @param redirectAttributes : holder for redirect attribute
     * @param model : holder for model attribute
     * @return The html page that allow to consult the medical record of the patient or the html page that allow edit
     * a note to the patient in case of binding error
     */
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

    /**
     * Perform a delete of a note to a patient
     * @param patientId : id of the patient to delete a note to
     * @param noteId : id of the note to delete
     * @param redirectAttributes : holder for redirect attribute
     * @param model : holder for model attribute
     * @return The html page that allow to consult the medical record of the patient
     */
    @GetMapping("/delete")
    public String delete(@RequestParam int patientId, @RequestParam String noteId, RedirectAttributes redirectAttributes, Model model) {
        logger.info("delete(" + patientId + "," + noteId + "," + model + ")");

        noteServiceInterface.delete(noteId);

        redirectAttributes.addAttribute("patientId", patientId);

        return ("redirect:/patient/open");
    }
}