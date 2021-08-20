package userInterface.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userInterface.model.Note;
import userInterface.proxy.NoteProxy;

import java.util.List;

/**
 * This class is used to process requests related to patient note
 */
@Service
public class NoteService implements NoteServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private NoteProxy noteProxy;

    /**
     * Creates a new NoteService
     */
    public NoteService() {
        logger.info("PatientNoteService()");

    }

    /**
     * Creates a new NoteService with the specified NoteProxy
     * @param noteProxy : proxy that this service will use
     */
    public NoteService(NoteProxy noteProxy) {
        logger.info("PatientNoteService(" + noteProxy + ")");

        this.noteProxy = noteProxy;
    }

    @Override
    public void insert(Note note) {
        logger.info("insert(" + note + ")");

        noteProxy.insert(note);
    }

    @Override
    public Note select(String id) {
        logger.info("select(" + id + ")");

        return noteProxy.select(id);
    }

    @Override
    public List<Note> list(int patientId) {
        logger.info("list(" + patientId + ")");

        return noteProxy.list(patientId);
    }

    @Override
    public void update(String id, Note note) {
        logger.info("update(" + id + "," + note + ")");

        noteProxy.update(id, note);
    }

    @Override
    public void delete(String id) {
        logger.info("delete(" + id + ")");

        noteProxy.delete(id);
    }
}
