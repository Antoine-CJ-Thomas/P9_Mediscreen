package userInterface.service;

import org.springframework.stereotype.Component;
import userInterface.model.Patient;

import java.util.List;

@Component
public interface PatientServiceInterface {

    Patient select(int id);

    List<Patient> list();

    void update(int id, Patient patient);

    void delete(int id);

    void insert(Patient patient);
}
