package patientInformation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import patientInformation.model.Patient;

/**
 * This class is used to interact with the patient information database
 */
@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {

    /**
     * Find a Patient in the database by his last name
     * @param lastName : last name of the patient to find
     * @return The patient found
     */
    Patient findByLastName(String lastName);
}
