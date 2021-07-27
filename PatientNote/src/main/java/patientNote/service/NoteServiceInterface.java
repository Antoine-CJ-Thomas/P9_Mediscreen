package patientNote.service;

import org.bson.types.ObjectId;
import patientNote.model.Note;

import java.util.List;

public interface NoteServiceInterface {

    Note select(String id);

    List<Note> list(int patientId);

    void update(String id, Note note);

    void delete(String id);
}
