package patientNote.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patientNote.model.Note;
import patientNote.repository.NoteRepository;

import java.util.List;

/**
 * This class is used to process requests related to patient note
 */
@Service
public class NoteService implements NoteServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private NoteRepository noteRepository;

    /**
     * Creates a new NoteService
     */
    public NoteService() {
        logger.info("PatientService()");
    }

    /**
     * Creates a new NoteService with the specified NoteRepository
     * @param noteRepository : repository that this service will use
     */
    public NoteService(NoteRepository noteRepository) {
        logger.info("PatientService(" + noteRepository + ")");

        this.noteRepository = noteRepository;
    }

    @Override
    public String insert(Note note) {
        logger.info("insert(" + note + ")");

        String message = "Invalid informations";

        if (note.getPatientId() != null) {

            if (note.getDate() != null) {

                if (note.getCommentary() != null) {

                    noteRepository.save(note);

                    message = "OK";
                }
            }
        }

        return message;
    }

    @Override
    public Note select(String id) {
        logger.info("select(" + id + ")");

        Note note = null;

        if (noteRepository.existsById(id)) {

            note = noteRepository.findById(id).get();
        }

        return note;
    }

    @Override
    public List<Note> list(int patientId) {
        logger.info("list(" + patientId + ")");

        List<Note> noteList = noteRepository.findByPatientId(patientId);

        return noteList;
    }

    @Override
    public String update(String id, Note note) {
        logger.info("update(" + id + "," + note + ")");

        String message = "Invalid id";

        if (noteRepository.existsById(id)) {

            note.setId(id);

            noteRepository.save(note);

            message = "OK";
        }

        return message;
    }

    @Override
    public String delete(String id) {
        logger.info("delete(" + id + ")");

        String message = "Invalid id";

        if (noteRepository.existsById(id)) {

            noteRepository.deleteById(id);

            message = "OK";
        }

        return message;
    }
}
