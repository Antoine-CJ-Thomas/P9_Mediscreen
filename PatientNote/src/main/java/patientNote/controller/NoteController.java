package patientNote.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import patientNote.model.Note;
import patientNote.service.NoteService;
import patientNote.service.NoteServiceInterface;

@RestController
@RequestMapping("/note")
public class NoteController {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private NoteServiceInterface noteServiceInterface;

    public NoteController() {
        logger.info("PatientNoteController()");

        noteServiceInterface = new NoteService();
    }

    public NoteController(NoteServiceInterface noteServiceInterface) {
        logger.info("PatientNoteController(" + noteServiceInterface + ")");

        this.noteServiceInterface = noteServiceInterface;
    }

    @PostMapping("/insert")
    public void insert(@RequestBody Note note) {
        logger.info("insert(" + note + ")");

        noteServiceInterface.insert(note);
    }

    @GetMapping("/select")
    public String select(@RequestParam String id) throws JsonProcessingException {
        logger.info("select(" + id + ")");

        return objectMapper.writeValueAsString(noteServiceInterface.select(id));
    }

    @GetMapping("/list")
    public String list(@RequestParam int patientId) throws JsonProcessingException {
        logger.info("list(" + patientId + ")");

        return objectMapper.writeValueAsString(noteServiceInterface.list(patientId));
    }

    @PutMapping("/update")
    public void update(@RequestParam String id, @RequestBody Note note) {
        logger.info("update(" + id + "," + note + ")");

        noteServiceInterface.update(id, note);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String id) {
        logger.info("delete(" + id + ")");

        noteServiceInterface.delete(id);
    }
}