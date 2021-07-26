package patientInformation.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patientInformation.model.Patient;
import patientInformation.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService implements PatientServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private PatientRepository patientRepository;

    public PatientService() {
        logger.info("PatientService()");
    }

    public PatientService(PatientRepository patientRepository) {
        logger.info("PatientService(" + patientRepository + ")");

        this.patientRepository = patientRepository;
    }

    @Override
    public void insert(Patient patient) {
        logger.info("insert(" + patient + ")");

        patientRepository.save(patient);
    }

    @Override
    public Patient select(int id) {
        logger.info("select(" + id + ")");

        Optional<Patient> patientOptional = patientRepository.findById(id);

        return patientOptional.get();
    }

    @Override
    public List<Patient> list() {
        logger.info("list()");

        return (List<Patient>) patientRepository.findAll();
    }

    @Override
    public void update(int id, Patient patient) {
        logger.info("update(" + id + "," + patient + ")");

        patient.setId(id);

        patientRepository.save(patient);
    }

    @Override
    public void delete(int id) {
        logger.info("delete(" + id + ")");

        patientRepository.deleteById(id);
    }
}
