package patientReport.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used to stores the data of a diabetes report
 */
@Component
public class DiabetesReport {

    private int age;
    private String gender;
    private Date birthDate;
    private String riskLevel;
    private List<String> commentaryList = new ArrayList<>();
    private List<String> medicalTriggerTermList = new ArrayList<>();
    private List<String> generalTriggerTermList = new ArrayList<>();

    public DiabetesReport() {

    }

    public DiabetesReport(String gender, Date birthDate, List<String> commentaryList) {

        this.gender = gender;
        this.birthDate = birthDate;
        this.commentaryList = commentaryList;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getCommentaryList() {
        return commentaryList;
    }

    public void setCommentaryList(List<String> commentaryList) {
        this.commentaryList = commentaryList;
    }

    public List<String> getMedicalTriggerTermList() {
        return medicalTriggerTermList;
    }

    public void setMedicalTriggerTermList(List<String> medicalTriggerTermList) {
        this.medicalTriggerTermList = medicalTriggerTermList;
    }

    public List<String> getGeneralTriggerTermList() {
        return generalTriggerTermList;
    }

    public void setGeneralTriggerTermList(List<String> generalTriggerTermList) {
        this.generalTriggerTermList = generalTriggerTermList;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
}