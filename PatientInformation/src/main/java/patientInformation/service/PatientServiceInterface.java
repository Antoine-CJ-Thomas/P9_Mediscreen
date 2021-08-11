package patientInformation.service;

import org.springframework.stereotype.Component;
import patientInformation.model.Patient;

import java.util.List;

/**
 * This interface is used to implement patient service methods
 */
@Component
public interface PatientServiceInterface {

    /**
     * Insert a new Patient in the database
     * @param patient : patient to insert
     * @return A message "OK" if process success or an error message if process fail
     */
    String insert(Patient patient);

    /**
     * Select a Patient in the database by his id
     * @param id : id of the patient to select
     * @return The selected patient
     */
    Patient selectById(int id);

    /**
     * Select a Patient in the database by his last name
     * @param lastName : last name of the patient to select
     * @return The selected patient
     */
    Patient selectByLastName(String lastName);

    /**
     * Select the Patient List in the database
     * @return The selected patient list
     */
    List<Patient> list();

    /**
     * Update the Patient in the database
     * @param id : id of the patient to update
     * @param patient : data of the patient to update
     * @return A message "OK" if process success or an error message if process fail
     */
    String update(int id, Patient patient);

    /**
     * Delete the Patient in the database
     * @param id : id of the patient to delete
     * @return A message "OK" if process success or an error message if process fail
     */
    String delete(int id);
}