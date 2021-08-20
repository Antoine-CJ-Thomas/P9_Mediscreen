package unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import patientInformation.controller.PatientController;
import patientInformation.model.Patient;
import patientInformation.service.PatientServiceInterface;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientControllerUnitTest {

    private PatientController patientController;

    private ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);
    private PatientServiceInterface patientServiceInterface = Mockito.mock(PatientServiceInterface.class);
    private HttpServletResponse httpServletResponse = Mockito.mock(HttpServletResponse.class);

    @BeforeEach
    public void beforeEach() {

        patientController = new PatientController(objectMapper, patientServiceInterface);
    }

    @Test
    public void insert_status_200() throws IOException {

        //GIVEN
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        Mockito.when(patientServiceInterface.insert(patient)).thenReturn("OK");

        patientController.insert(patient, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).setStatus(200);
    }

    @Test
    public void insert_status_400() throws IOException {

        //GIVEN
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        Mockito.when(patientServiceInterface.insert(patient)).thenReturn("Patient could not be inserted");

        patientController.insert(patient, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).sendError(400, "Patient could not be inserted");
    }

    @Test
    public void selectById_status_200() throws IOException {

        //GIVEN
        int id = 1;
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        Mockito.when(patientServiceInterface.selectById(id)).thenReturn(patient);
        Mockito.when(objectMapper.writeValueAsString(patient)).thenReturn("patient");

        patientController.selectById(id, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).setStatus(200);
    }

    @Test
    public void selectById_status_404() throws IOException {

        //GIVEN
        int id = 1;

        //WHEN
        Mockito.when(patientServiceInterface.selectById(id)).thenReturn(null);

        patientController.selectById(id, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).sendError(404, "Patient with (id = " + id + ") could not be found");
    }

    @Test
    public void selectByLastName_status_200() throws IOException {

        //GIVEN
        String lastName = "lastName";
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        Mockito.when(patientServiceInterface.selectByLastName(lastName)).thenReturn(patient);
        Mockito.when(objectMapper.writeValueAsString(patient)).thenReturn("patient");

        patientController.selectByLastName(lastName, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).setStatus(200);
    }

    @Test
    public void selectByLastName_status_404() throws IOException {

        //GIVEN
        String lastName = "lastName";

        //WHEN
        Mockito.when(patientServiceInterface.selectByLastName(lastName)).thenReturn(null);

        patientController.selectByLastName(lastName, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).sendError(404, "Patient with (lastName = " + lastName + ") could not be found");
    }

    @Test
    public void list_status_200() throws IOException {

        //GIVEN
        List<Patient> patientList = new ArrayList<>();

        //WHEN
        Mockito.when(patientServiceInterface.list()).thenReturn(patientList);

        patientController.list(httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).setStatus(200);
    }

    @Test
    public void list_status_404() throws IOException {

        //GIVEN
        List<Patient> patientList = new ArrayList<>();

        //WHEN
        Mockito.when(patientServiceInterface.list()).thenReturn(null);
        Mockito.when(objectMapper.writeValueAsString(patientList)).thenReturn("patientList");

        patientController.list(httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).sendError(404, "No patient could be found");
    }

    @Test
    public void update_status_200() throws IOException {

        //GIVEN
        int id = 1;
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        Mockito.when(patientServiceInterface.update(id, patient)).thenReturn("OK");

        patientController.update(id, patient, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).setStatus(200);
    }

    @Test
    public void update_status_404() throws IOException {

        //GIVEN
        int id = 1;
        Patient patient = Mockito.mock(Patient.class);

        //WHEN
        Mockito.when(patientServiceInterface.update(id, patient)).thenReturn("Invalid id");

        patientController.update(id, patient, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).sendError(400, "Invalid id");
    }

    @Test
    public void delete_status_200() throws IOException {

        //GIVEN
        int id = 1;

        //WHEN
        Mockito.when(patientServiceInterface.delete(id)).thenReturn("OK");

        patientController.delete(id, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).setStatus(200);
    }

    @Test
    public void delete_status_404() throws IOException {

        //GIVEN
        int id = 1;

        //WHEN
        Mockito.when(patientServiceInterface.delete(id)).thenReturn("Invalid id");

        patientController.delete(id, httpServletResponse);

        //THEN
        Mockito.verify(httpServletResponse, Mockito.times(1)).sendError(400, "Invalid id");
    }
}