package unit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import userInterface.model.Patient;
import userInterface.proxy.PatientProxy;
import userInterface.service.PatientService;

import java.util.List;

public class PatientServiceUnitTest {

    private PatientService patientService;

    private PatientProxy mockedPatientProxy = Mockito.mock(PatientProxy.class);

    @BeforeEach
    public void beforeEach() {

        patientService = new PatientService(mockedPatientProxy);
    }

    @Test
    public void insert() {

        //GIVEN
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        patientService.insert(patient);

        //THEN
        Mockito.verify(mockedPatientProxy, Mockito.times(1)).insert(patient);
    }

    @Test
    public void select() {

        //GIVEN
        int patientId = 1;
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        Mockito.when(mockedPatientProxy.selectById(patientId)).thenReturn(patient);

        //THEN
        Assertions.assertThat(patientService.selectById(patientId) == patient);
    }

    @Test
    public void list() {

        //GIVEN
        List<Patient> patientList = Mockito.mock(List.class);

        //WHEN
        Mockito.when(mockedPatientProxy.list()).thenReturn(patientList);

        //THEN
        Assertions.assertThat(patientService.list() == patientList);
    }

    @Test
    public void update() {

        //GIVEN
        int patientId = 1;
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        patientService.update(patientId, patient);

        //THEN
        Mockito.verify(mockedPatientProxy, Mockito.times(1)).update(patientId, patient);
    }

    @Test
    public void delete() {

        //GIVEN
        int patientId = 1;

        //WHEN
        patientService.delete(patientId);

        //THEN
        Mockito.verify(mockedPatientProxy, Mockito.times(1)).delete(patientId);
    }
}