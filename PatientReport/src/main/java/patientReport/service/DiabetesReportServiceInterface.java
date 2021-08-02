package patientReport.service;

import org.springframework.stereotype.Component;
import patientReport.model.DiabetesReport;

@Component
public interface DiabetesReportServiceInterface {

    void setGeneralTriggerTerm(DiabetesReport diabetesReport);

    void findMedicalTriggerTerm(DiabetesReport diabetesReport);

    void evaluateRiskLevel(DiabetesReport diabetesReport);
}