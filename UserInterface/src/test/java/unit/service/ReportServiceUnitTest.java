package unit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import userInterface.model.DiabetesReport;
import userInterface.model.Note;
import userInterface.model.Patient;
import userInterface.proxy.PatientProxy;
import userInterface.proxy.ReportProxy;
import userInterface.service.PatientService;
import userInterface.service.ReportService;

import java.util.ArrayList;
import java.util.List;

public class ReportServiceUnitTest {

    private ReportService reportService;

    private ReportProxy mockedReportProxy = Mockito.mock(ReportProxy.class);

    @BeforeEach
    public void beforeEach() {

        reportService = new ReportService(mockedReportProxy);
    }

    @Test
    public void getDiabetesReport() {

        //GIVEN
        Patient patient = Mockito.mock(Patient.class);
        Note note = Mockito.mock(Note.class);
        List<Note> noteList = new ArrayList();

        //WHEN
        Mockito.when(note.getCommentary()).thenReturn("commentary");

        noteList.add(note);

        reportService.getDiabetesReport(patient, noteList);

        //THEN
        Mockito.verify(mockedReportProxy, Mockito.times(1)).assessDiabetes(Mockito.any(DiabetesReport.class));
    }
}