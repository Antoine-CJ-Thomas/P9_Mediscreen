package unit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import patientInformation.model.Patient;
import patientInformation.repository.PatientRepository;
import patientInformation.service.PatientService;

import java.util.Arrays;
import java.util.Optional;

public class PatientServiceUnitTest {

    private PatientService patientService;

    private PatientRepository mockedPatientRepository = Mockito.mock(PatientRepository.class);

    @BeforeEach
    public void beforeEach() {

        patientService = new PatientService(mockedPatientRepository);
    }

    @Test
    public void insert() {

        //GIVEN
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        patientService.insert(patient);

        //THEN
        Mockito.verify(mockedPatientRepository, Mockito.times(1)).save(patient);
    }

    @Test
    public void select() {

        //GIVEN
        int id = 1;
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        Mockito.when(mockedPatientRepository.findById(id)).thenReturn(Optional.of(patient));

        //THEN
        Assertions.assertThat(patientService.select(id) == patient);
    }

    @Test
    public void list() {

        //GIVEN
        Patient patient = Mockito.mock(Patient.class);
        Iterable<Patient> patientIterable = Arrays.asList(patient);

        //WHEN
        Mockito.when(mockedPatientRepository.findAll()).thenReturn(patientIterable);

        //THEN
        Assertions.assertThat(patientService.list() == patientIterable);
    }

    @Test
    public void update() {

        //GIVEN
        int id = 1;
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        patientService.update(id, patient);

        //THEN
        Mockito.verify(mockedPatientRepository, Mockito.times(1)).save(patient);
    }

    @Test
    public void delete() {

        //GIVEN
        int id = 1;

        //WHEN
        patientService.delete(id);

        //THEN
        Mockito.verify(mockedPatientRepository, Mockito.times(1)).deleteById(id);
    }
}