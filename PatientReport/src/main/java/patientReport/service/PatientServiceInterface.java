package patientReport.service;

import org.springframework.stereotype.Component;
import patientReport.model.Patient;

/**
 * This interface is used to implement patient service methods
 */
@Component
public interface PatientServiceInterface {

    /**
     * Select a Patient by his id
     * @param id : id of the patient to select
     * @return The selected patient
     */
    Patient selectById(int id);

    /**
     * Select a Patient by his last name
     * @param lastName : last name of the patient to select
     * @return The selected patient
     */
    Patient selectByLastName(String lastName);
}
