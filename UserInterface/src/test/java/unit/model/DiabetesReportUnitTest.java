package unit.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import userInterface.model.DiabetesReport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiabetesReportUnitTest {

    private DiabetesReport diabetesReport;

    @BeforeEach
    public void beforeEach() {

        diabetesReport = new DiabetesReport();
    }

    @Test
    public void setAndGetAge() {

        //GIVEN
        int age = 1;

        //WHEN
        diabetesReport.setAge(age);

        //THEN
        Assertions.assertThat(diabetesReport.getAge() == age);
    }

    @Test
    public void setAndGetGender() {

        //GIVEN
        String gender = "M";

        //WHEN
        diabetesReport.setGender(gender);

        //THEN
        Assertions.assertThat(diabetesReport.getGender().equals(gender));
    }

    @Test
    public void setAndGetBirthDate() {

        //GIVEN
        Date birthDate = new Date();

        //WHEN
        diabetesReport.setBirthDate(birthDate);

        //THEN
        Assertions.assertThat(diabetesReport.getBirthDate() == birthDate);
    }

    @Test
    public void setAndGetCommentaryList() {

        //GIVEN
        List<String> commentaryList = new ArrayList();

        //WHEN
        diabetesReport.setCommentaryList(commentaryList);

        //THEN
        Assertions.assertThat(diabetesReport.getCommentaryList() == commentaryList);
    }

    @Test
    public void setAndGetMedicalTriggerTermList() {

        //GIVEN
        List<String> medicalTriggerTermList = new ArrayList();

        //WHEN
        diabetesReport.setMedicalTriggerTermList(medicalTriggerTermList);

        //THEN
        Assertions.assertThat(diabetesReport.getMedicalTriggerTermList() == medicalTriggerTermList);
    }

    @Test
    public void setAndGetGeneralTriggerTermList() {

        //GIVEN
        List<String> generalTriggerTermList = new ArrayList();

        //WHEN
        diabetesReport.setGeneralTriggerTermList(generalTriggerTermList);

        //THEN
        Assertions.assertThat(diabetesReport.getGeneralTriggerTermList() == generalTriggerTermList);
    }

    @Test
    public void setAndGetRiskLevel() {

        //GIVEN
        String riskLevel = "riskLevel";

        //WHEN
        diabetesReport.setRiskLevel(riskLevel);

        //THEN
        Assertions.assertThat(diabetesReport.getRiskLevel().equals(riskLevel));
    }
}