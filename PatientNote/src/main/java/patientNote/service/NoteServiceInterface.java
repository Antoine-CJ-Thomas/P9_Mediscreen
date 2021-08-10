package patientNote.service;

import org.springframework.stereotype.Component;
import patientNote.model.Note;

import java.util.List;

@Component
public interface NoteServiceInterface {

    String insert(Note note);

    Note select(String id);

    List<Note> list(int patientId);

    String update(String id, Note note);

    String delete(String id);
}
