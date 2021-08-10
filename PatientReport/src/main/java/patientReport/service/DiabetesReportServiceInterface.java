package patientReport.service;

import org.springframework.stereotype.Component;
import patientReport.model.DiabetesReport;

/**
 * This interface is used to implement report service methods
 */
@Component
public interface DiabetesReportServiceInterface {

    /**
     * Complete a diabetes report by evaluating the risk level of contracting it
     * @param diabetesReport : diabetes report that contains needed patient informations
     * @return The diabetes report completed
     */
    String assessDiabetes(DiabetesReport diabetesReport);
}