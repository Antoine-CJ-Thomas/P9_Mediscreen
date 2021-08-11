package patientReport.service;

import org.springframework.stereotype.Component;
import patientReport.model.Note;

import java.util.List;

/**
 * This interface is used to implement note service methods
 */
@Component
public interface NoteServiceInterface {

    /**
     * Select the Note list with a specific patient id
     * @param patientId : id of the patient to find
     * @return The selected note list
     */
    List<Note> list(int patientId);
}
