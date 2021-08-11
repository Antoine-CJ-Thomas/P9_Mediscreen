package patientNote.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import patientNote.model.Note;

import java.util.List;
import java.util.Optional;

/**
 * This class is used to interact with the patient note database
 */
@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    /**
     * Find a Note by its id in the database
     * @param id : id of the note to find
     * @return The found note
     */
    Optional<Note> findById(String id);

    /**
     * Find the Note list by their patient id in the database
     * @param patientId : id of the patient to find
     * @return The found note list
     */
    List<Note> findByPatientId(Integer patientId);
}
