package patientInformation.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patientInformation.model.Patient;
import patientInformation.repository.PatientRepository;

import java.util.List;

/**
 * This class is used to process requests related to patient information
 */
@Service
public class PatientService implements PatientServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Creates a new PatientService
     */
    public PatientService() {
        logger.info("PatientService()");
    }

    /**
     * Creates a new PatientService with the specified PatientRepository
     * @param patientRepository : repository that this service will use
     */
    public PatientService(PatientRepository patientRepository) {
        logger.info("PatientService(" + patientRepository + ")");

        this.patientRepository = patientRepository;
    }

    @Override
    public String insert(Patient patient) {
        logger.info("insert(" + patient + ")");

        String message = "Invalid informations";

        if (patient.getFirstName().length() > 1 && patient.getFirstName().length() < 32) {

            if (patient.getLastName().length() > 1 && patient.getLastName().length() < 32) {

                if (patient.getBirthDate() != null) {

                    if (patient.getGender().length() == 1) {

                        if (patient.getAddress().length() > 1 && patient.getAddress().length() < 64) {

                            if (patient.getPhoneNumber().length() > 1 && patient.getPhoneNumber().length() < 15) {

                                patientRepository.save(patient);

                                message = "OK";
                            }
                        }
                    }
                }
            }
        }

        return message;
    }

    @Override
    public Patient select(int id) {
        logger.info("select(" + id + ")");

        Patient patient = null;

        if (patientRepository.existsById(id)) {

            patient = patientRepository.findById(id).get();
        }

        return patient;
    }

    @Override
    public List<Patient> list() {
        logger.info("list()");

        List<Patient> patientList = (List<Patient>) patientRepository.findAll();

        return patientList;
    }

    @Override
    public String update(int id, Patient patient) {
        logger.info("update(" + id + "," + patient + ")");

        String message = "Invalid id";

        if (patientRepository.existsById(id)) {

            patient.setId(id);

            patientRepository.save(patient);

            message = "OK";
        }

        return message;
    }

    @Override
    public String delete(int id) {
        logger.info("delete(" + id + ")");

        String message = "Invalid id";

        if (patientRepository.existsById(id)) {

            patientRepository.deleteById(id);

            message = "OK";
        }

        return message;
    }
}