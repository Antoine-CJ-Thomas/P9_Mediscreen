package patientNote.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patientNote.model.Note;
import patientNote.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

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
    public void insert(Note note) {
        logger.info("insert(" + note + ")");

        noteRepository.save(note);
    }

    @Override
    public Note select(String id) {
        logger.info("select(" + id + ")");

        Optional<Note> patientOptional = noteRepository.findById(id);

        return patientOptional.get();
    }

    @Override
    public List<Note> list(int patientId) {
        logger.info("list(" + patientId + ")");

        return noteRepository.findByPatientId(patientId);
    }

    @Override
    public void update(String id, Note note) {
        logger.info("update(" + id + "," + note + ")");

        note.setId(id);

        noteRepository.save(note);
    }

    @Override
    public void delete(String id) {
        logger.info("delete(" + id + ")");

        noteRepository.deleteById(id);
    }
}
