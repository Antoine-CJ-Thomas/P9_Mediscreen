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

/**
 * This class is used to intercept requests related to patient note
 */
@RestController
@RequestMapping("/note")
public class NoteController {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    private ObjectMapper objectMapper;

    @Autowired
    private NoteServiceInterface noteServiceInterface;

    /**
     * Creates a new NoteController
     */
    public NoteController() {
        logger.info("PatientNoteController()");

        objectMapper = new ObjectMapper();
        noteServiceInterface = new NoteService();
    }

    /**
     * Creates a new NoteController with the specified ObjectMapper and NoteServiceInterface
     * @param objectMapper : mapper that this controller will use
     * @param noteServiceInterface : service that this controller will use
     */
    public NoteController(ObjectMapper objectMapper, NoteServiceInterface noteServiceInterface) {
        logger.info("PatientNoteController(" + noteServiceInterface + "," + objectMapper + ")");

        this.objectMapper = objectMapper;
        this.noteServiceInterface = noteServiceInterface;
    }

    /**
     * Insert a new Note in the database
     * @param note : note to add in the database
     * @param httpServletResponse : http response
     * @throws IOException : if httpServletResponse is null
     */
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

    /**
     * Select a Note in the database
     * @param id : id of the note to select
     * @param httpServletResponse : http response
     * @return The selected note (JSon)
     * @throws IOException : if httpServletResponse is null
     */
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

    /**
     * Select the Note list in the database
     * @param httpServletResponse : http response
     * @param patientId : id of the patient to find
     * @return The selected note list (JSon)
     * @throws IOException : if httpServletResponse is null
     */
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

    /**
     * Update the Note in the database
     * @param id : id of the note to update
     * @param note : data of the note to update
     * @param httpServletResponse : http response
     * @throws IOException : if httpServletResponse is null
     */
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

    /**
     * Delete the Note in the database
     * @param id : id of the note to delete
     * @param httpServletResponse : http response
     * @throws IOException : if httpServletResponse is null
     */
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