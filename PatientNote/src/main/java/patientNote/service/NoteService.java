package patientNote.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patientNote.model.Note;
import patientNote.repository.NoteRepository;

import java.util.List;

@Service
public class NoteService implements NoteServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private NoteRepository noteRepository;

    public NoteService() {
        logger.info("PatientService()");
    }

    public NoteService(NoteRepository noteRepository) {
        logger.info("PatientService(" + noteRepository + ")");

        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> list(int patientId) {
        logger.info("list(" + patientId + ")");

        return noteRepository.findByPatientId(patientId);
    }
}
