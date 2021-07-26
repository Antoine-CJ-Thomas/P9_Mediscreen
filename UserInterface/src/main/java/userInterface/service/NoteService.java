package userInterface.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userInterface.model.Note;
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
    public List<Note> list(int id) {
        logger.info("list(" + id + ")");

        return noteProxy.list(id);
    }
}
