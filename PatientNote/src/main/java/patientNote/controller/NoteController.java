package patientNote.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import patientNote.model.Note;
import patientNote.service.NoteService;
import patientNote.service.NoteServiceInterface;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    private ObjectMapper objectMapper;

    @Autowired
    private NoteServiceInterface noteServiceInterface;

    public NoteController() {
        logger.info("PatientNoteController()");

        objectMapper = new ObjectMapper();
        noteServiceInterface = new NoteService();
    }

    public NoteController(ObjectMapper objectMapper, NoteServiceInterface noteServiceInterface) {
        logger.info("PatientNoteController(" + noteServiceInterface + "," + objectMapper + ")");

        this.objectMapper = objectMapper;
        this.noteServiceInterface = noteServiceInterface;
    }

    @PostMapping("/insert")
    public void insert(@RequestBody Note note, HttpServletResponse httpServletResponse) throws IOException {
        logger.info("insert(" + note + ")");

        String message = noteServiceInterface.insert(note);;

        if (message.equals("OK")) {

            httpServletResponse.setStatus(200);
        }

        else {

            httpServletResponse.sendError(400, message);
        }
    }

    @GetMapping("/select")
    public String select(@RequestParam String id, HttpServletResponse httpServletResponse) throws IOException {
        logger.info("select(" + id + ")");

        Note note = noteServiceInterface.select(id);

        if (note != null) {

            httpServletResponse.setStatus(200);
        }

        else {

            httpServletResponse.sendError(404, "Note with (id = " + id + ") could not be found");
        }

        return objectMapper.writeValueAsString(note);
    }

    @GetMapping("/list")
    public String list(@RequestParam int patientId, HttpServletResponse httpServletResponse) throws IOException {
        logger.info("list(" + patientId + ")");

        List<Note> noteList = noteServiceInterface.list(patientId);

        if (noteList != null) {

            httpServletResponse.setStatus(200);
        }

        else {

            httpServletResponse.sendError(404, "No patient could be found");
        }

        return objectMapper.writeValueAsString(noteList);
    }

    @PutMapping("/update")
    public void update(@RequestParam String id, @RequestBody Note note, HttpServletResponse httpServletResponse) throws IOException {
        logger.info("update(" + id + "," + note + ")");

        String message = noteServiceInterface.update(id, note);

        if (message.equals("OK")) {

            httpServletResponse.setStatus(200);
        }

        else {

            httpServletResponse.sendError(400, message);
        }
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String id, HttpServletResponse httpServletResponse) throws IOException {
        logger.info("delete(" + id + ")");

        String message = noteServiceInterface.delete(id);

        if (message.equals("OK")) {

            httpServletResponse.setStatus(200);
        }

        else {

            httpServletResponse.sendError(400, message);
        }
    }
}