package patientNote.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/list")
    public String list(@RequestParam int id) throws JsonProcessingException {
        logger.info("list(" + id + ")");

        return objectMapper.writeValueAsString(noteServiceInterface.list(id));
    }
}
