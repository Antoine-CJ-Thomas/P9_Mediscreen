package userInterface.service;

import org.springframework.stereotype.Component;
import userInterface.model.Note;

import java.util.List;

/**
 * This interface is used to implement note service methods
 */
@Component
public interface NoteServiceInterface {

    /**
     * Insert a new Note
     * @param note : note to insert
     */
    void insert(Note note);

    /**
     * Select a Note
     * @param id : id of the note to select
     * @return The selected note
     */
    Note select(String id);

    /**
     * Select the Note list with a specific patient id
     * @param patientId : id of the patient to find
     * @return The selected note list
     */
    List<Note> list(int patientId);

    /**
     * Update the Note
     * @param id : id of the note to update
     * @param note : data of the note to update
     */
    void update(String id, Note note);

    /**
     * Delete the Note
     * @param id : id of the note to delete
     */
    void delete(String id);
}
