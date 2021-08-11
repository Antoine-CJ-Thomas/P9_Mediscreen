package patientReport.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patientReport.model.Patient;
import patientReport.proxy.PatientProxy;

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
    public Patient selectById(int id) {
        logger.info("selectById(" + id + ")");

        return patientProxy.selectById(id);
    }

    @Override
    public Patient selectByLastName(String lastName) {
        logger.info("selectByLastName(" + lastName + ")");

        return patientProxy.selectByLastName(lastName);
    }
}
