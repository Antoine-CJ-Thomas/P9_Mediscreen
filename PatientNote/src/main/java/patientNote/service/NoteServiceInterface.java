package patientNote.service;

import org.springframework.stereotype.Component;
import patientNote.model.Note;

import java.util.List;

/**
 * This interface is used to implement note service methods
 */
@Component
public interface NoteServiceInterface {

    /**
     * Insert a new Note in the database
     * @param note : note to insert
     * @return A message "OK" if process success or an error message if process fail
     */
    String insert(Note note);

    /**
     * Select a Note in the database
     * @param id : id of the note to select
     * @return The selected note
     */
    Note select(String id);

    /**
     * Select the Note list in the database
     * @param patientId : id of the patient to find
     * @return The selected note list
     */
    List<Note> list(int patientId);

    /**
     * Update the Note in the database
     * @param id : id of the note to update
     * @param note : data of the note to update
     * @return A message "OK" if process success or an error message if process fail
     */
    String update(String id, Note note);

    /**
     * Delete the Note in the database
     * @param id : id of the note to delete
     * @return A message "OK" if process success or an error message if process fail
     */
    String delete(String id);
}