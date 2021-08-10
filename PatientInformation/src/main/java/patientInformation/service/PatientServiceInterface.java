package patientInformation.service;

import org.springframework.stereotype.Component;
import patientInformation.model.Patient;

import java.util.List;

@Component
public interface PatientServiceInterface {

    String insert(Patient patient);

    Patient select(int id);

    List<Patient> list();

    String update(int id, Patient patient);

    String delete(int id);
}
