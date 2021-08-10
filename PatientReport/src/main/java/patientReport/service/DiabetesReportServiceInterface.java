package patientReport.service;

import org.springframework.stereotype.Component;
import patientReport.model.DiabetesReport;

@Component
public interface DiabetesReportServiceInterface {

    String assessDiabetes(DiabetesReport diabetesReport);
}