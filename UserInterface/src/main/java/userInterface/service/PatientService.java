package userInterface.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userInterface.model.Patient;
import userInterface.proxy.PatientProxy;

import java.util.List;

/**
 * This class is used to process requests related to patient information
 */
@Service
public class PatientService implements PatientServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private PatientProxy patientProxy;

    /**
     * Creates a new PatientService
     */
    public PatientService() {
        logger.info("PatientInformationService()");

    }

    /**
     * Creates a new PatientService with the specified PatientProxy
     * @param patientProxy : proxy that this service will use
     */
    public PatientService(PatientProxy patientProxy) {
        logger.info("PatientInformationService(" + patientProxy + ")");

        this.patientProxy = patientProxy;
    }

    @Override
    public void insert(Patient patient) {
        logger.info("insert(" + patient + ")");

        patientProxy.insert(patient);
    }

    @Override
    public Patient selectById(int id) {
        logger.info("selectById(" + id + ")");

        return patientProxy.selectById(id);
    }

    @Override
    public List<Patient> list() {
        logger.info("list()");

        return patientProxy.list();
    }

    @Override
    public void update(int id, Patient patient) {
        logger.info("update(" + id + "," + patient + ")");

        patientProxy.update(id, patient);
    }

    @Override
    public void delete(int id) {
        logger.info("delete(" + id + ")");

        patientProxy.delete(id);
    }
}
