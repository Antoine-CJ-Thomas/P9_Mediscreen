package userInterface.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import userInterface.model.Note;

import java.util.List;

@Component
public interface NoteServiceInterface {

    Note select(String id);

    List<Note> list(int patientId);

    void update(String id, Note note);

    void delete(String id);
}
