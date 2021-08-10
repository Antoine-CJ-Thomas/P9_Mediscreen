package userInterface.service;

import org.springframework.stereotype.Component;
import userInterface.model.Patient;

import java.util.List;

/**
 * This interface is used to implement patient service methods
 */
@Component
public interface PatientServiceInterface {

    /**
     * Insert a new Patient
     * @param patient : patient to insert
     */
    void insert(Patient patient);

    /**
     * Select a Patient
     * @param id : id of the patient to select
     * @return The selected patient
     */
    Patient select(int id);

    /**
     * Select the Patient List
     * @return The selected patient list
     */
    List<Patient> list();

    /**
     * Update the Patient
     * @param id : id of the patient to update
     * @param patient : data of the patient to update
     */
    void update(int id, Patient patient);

    /**
     * Delete the Patient
     * @param id : id of the patient to delete
     */
    void delete(int id);
}
