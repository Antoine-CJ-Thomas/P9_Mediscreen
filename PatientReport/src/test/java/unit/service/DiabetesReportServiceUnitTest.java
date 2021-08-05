package unit.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import patientReport.model.DiabetesReport;
import patientReport.service.DiabetesReportService;
import patientReport.util.FileLineReaderInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class DiabetesReportServiceUnitTest {

    private DiabetesReportService diabetesReportService;

    private FileLineReaderInterface fileLineReaderInterface = Mockito.mock(FileLineReaderInterface.class);

    @BeforeEach
    public void beforeEach() {

        diabetesReportService = new DiabetesReportService(fileLineReaderInterface);
    }

    @Test
    public void setGeneralTriggerTerm() {

        //GIVEN
        DiabetesReport diabetesReport = Mockito.mock(DiabetesReport.class);
        Date birthDate = new Date();
        String gender = "M";
        int age = 30;

        //WHEN
        Mockito.when(diabetesReport.getBirthDate()).thenReturn(birthDate);
        Mockito.when(diabetesReport.getAge()).thenReturn(age);
        Mockito.when(diabetesReport.getGender()).thenReturn(gender);

        diabetesReportService.setGeneralTriggerTerm(diabetesReport);

        //THEN
        Mockito.verify(diabetesReport, Mockito.times(2)).getGeneralTriggerTermList();
    }

    @Test
    public void findMedicalTriggerTerm() {

        //GIVEN
        String testTriggerTerm = "testTriggerTerm";
        DiabetesReport diabetesReport = Mockito.mock(DiabetesReport.class);

        List<String> commentaryList = new ArrayList();
        List<String> medicalTriggerTermList = new ArrayList();
        List<String> triggerTermList = new ArrayList();

        //WHEN
        Mockito.when(diabetesReport.getCommentaryList()).thenReturn(commentaryList);
        Mockito.when(diabetesReport.getMedicalTriggerTermList()).thenReturn(medicalTriggerTermList);
        Mockito.when(fileLineReaderInterface.getTriggerTermList()).thenReturn(triggerTermList);

        commentaryList.add(testTriggerTerm);
        triggerTermList.add(testTriggerTerm);

        diabetesReportService.findMedicalTriggerTerm(diabetesReport);

        //THEN
        Mockito.verify(diabetesReport, Mockito.times(2)).getMedicalTriggerTermList();
    }

    @Test
    public void evaluateRiskLevel() {

        //GIVEN
        DiabetesReport diabetesReport = Mockito.mock(DiabetesReport.class);
        List<String> medicalTriggerTermList = Mockito.mock(List.class);

        //WHEN
        Mockito.when(diabetesReport.getAge()).thenReturn(30);
        Mockito.when(diabetesReport.getMedicalTriggerTermList()).thenReturn(medicalTriggerTermList);
        Mockito.when(medicalTriggerTermList.size()).thenReturn(8);

        diabetesReportService.evaluateRiskLevel(diabetesReport);

        //THEN
        Mockito.verify(diabetesReport, Mockito.times(1)).setRiskLevel("Apparition précoce");
    }
}
