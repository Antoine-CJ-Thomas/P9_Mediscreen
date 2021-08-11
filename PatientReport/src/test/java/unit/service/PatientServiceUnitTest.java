package unit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import patientReport.model.Patient;
import patientReport.proxy.PatientProxy;
import patientReport.service.PatientService;

public class PatientServiceUnitTest {

    private PatientService patientService;

    private PatientProxy mockedPatientProxy = Mockito.mock(PatientProxy.class);

    @BeforeEach
    public void beforeEach() {

        patientService = new PatientService(mockedPatientProxy);
    }

    @Test
    public void selectById() {

        //GIVEN
        int patientId = 1;
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        Mockito.when(mockedPatientProxy.selectById(patientId)).thenReturn(patient);

        //THEN
        Assertions.assertThat(patientService.selectById(patientId) == patient);
    }

    @Test
    public void selectByLastName() {

        //GIVEN
        String lastName = "lastName";
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        Mockito.when(mockedPatientProxy.selectByLastName(lastName)).thenReturn(patient);

        //THEN
        Assertions.assertThat(patientService.selectByLastName(lastName) == patient);
    }
}