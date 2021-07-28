package userInterface.service;

import org.springframework.stereotype.Component;
import userInterface.model.Note;

import java.util.List;

@Component
public interface NoteServiceInterface {

    void insert(Note note);

    Note select(String id);

    List<Note> list(int patientId);

    void update(String id, Note note);

    void delete(String id);
}
