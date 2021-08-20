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

public class DiabetesReportServiceUnitTest {

    private DiabetesReportService diabetesReportService;

    private FileLineReaderInterface fileLineReaderInterface = Mockito.mock(FileLineReaderInterface.class);

    @BeforeEach
    public void beforeEach() {

        diabetesReportService = new DiabetesReportService(fileLineReaderInterface);
    }

    @Test
    public void assessDiabetes_success() {
        //GIVEN
        DiabetesReport diabetesReport = Mockito.mock(DiabetesReport.class);


        int age = 30;
        String gender = "M";
        Date birthDate = new Date();
        List<String> commentaryList = new ArrayList();
        List<String> medicalTriggerTermList = Mockito.mock(List.class);

        String testTriggerTerm = "testTriggerTerm";
        List<String> triggerTermList = new ArrayList();

        //WHEN
        Mockito.when(diabetesReport.getAge()).thenReturn(age);
        Mockito.when(diabetesReport.getGender()).thenReturn(gender);
        Mockito.when(diabetesReport.getBirthDate()).thenReturn(birthDate);
        Mockito.when(diabetesReport.getCommentaryList()).thenReturn(commentaryList);
        Mockito.when(diabetesReport.getMedicalTriggerTermList()).thenReturn(medicalTriggerTermList);
        Mockito.when(medicalTriggerTermList.size()).thenReturn(8);
        commentaryList.add(testTriggerTerm);

        Mockito.when(fileLineReaderInterface.getTriggerTermList()).thenReturn(triggerTermList);
        triggerTermList.add(testTriggerTerm);

        Mockito.when(diabetesReport.getRiskLevel()).thenReturn("riskLevel");

        //THEN
        Assertions.assertThat(diabetesReportService.assessDiabetes(diabetesReport) == "OK");
    }

    @Test
    public void assessDiabetes_failure() {

        //GIVEN
        DiabetesReport diabetesReport = Mockito.mock(DiabetesReport.class);

        int age = 20;
        String gender = "F";
        Date birthDate = new Date();
        List<String> commentaryList = new ArrayList();
        List<String> medicalTriggerTermList = Mockito.mock(List.class);

        String testTriggerTerm = "testTriggerTerm";
        List<String> triggerTermList = new ArrayList();

        //WHEN
        Mockito.when(diabetesReport.getAge()).thenReturn(age);
        Mockito.when(diabetesReport.getGender()).thenReturn(gender);
        Mockito.when(diabetesReport.getBirthDate()).thenReturn(birthDate);
        Mockito.when(diabetesReport.getCommentaryList()).thenReturn(commentaryList);
        Mockito.when(diabetesReport.getMedicalTriggerTermList()).thenReturn(medicalTriggerTermList);
        Mockito.when(medicalTriggerTermList.size()).thenReturn(8);
        commentaryList.add(testTriggerTerm);

        Mockito.when(fileLineReaderInterface.getTriggerTermList()).thenReturn(triggerTermList);
        triggerTermList.add(testTriggerTerm);

        Mockito.when(diabetesReport.getRiskLevel()).thenReturn(null);

        //THEN
        Assertions.assertThat(diabetesReportService.assessDiabetes(diabetesReport) == "Invalid informations");
    }
}