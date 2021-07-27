package userInterface.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userInterface.model.Note;
import userInterface.model.Patient;
import userInterface.proxy.NoteProxy;

import java.util.List;

@Service
public class NoteService implements NoteServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private NoteProxy noteProxy;

    public NoteService() {
        logger.info("PatientNoteService()");

    }

    public NoteService(NoteProxy noteProxy) {
        logger.info("PatientNoteService(" + noteProxy + ")");

        this.noteProxy = noteProxy;
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
