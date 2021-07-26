package userInterface.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userInterface.model.Patient;
import userInterface.proxy.PatientProxy;

import java.util.List;

@Service
public class PatientService implements PatientServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private PatientProxy patientProxy;

    public PatientService() {
        logger.info("PatientInformationService()");

    }

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
    public Patient select(int id) {
        logger.info("select(" + id + ")");

        return patientProxy.select(id);
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
