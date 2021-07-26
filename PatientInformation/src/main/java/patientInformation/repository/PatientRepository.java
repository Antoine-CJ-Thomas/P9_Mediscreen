package patientInformation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import patientInformation.model.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {
}
