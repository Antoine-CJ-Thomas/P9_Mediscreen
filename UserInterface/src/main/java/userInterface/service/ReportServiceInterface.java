package userInterface.service;

import org.springframework.stereotype.Component;
import userInterface.model.DiabetesReport;
import userInterface.model.Note;
import userInterface.model.Patient;

import java.util.List;

/**
 * This interface is used to implement report service methods
 */
@Component
public interface ReportServiceInterface {

    /**
     * Complete a diabetes report by evaluating the risk level of contracting it
     * @param patient : patient that will be evaluated
     * @param noteList : list of all notes of the patient
     * @return The diabetes report completed
     */
    DiabetesReport getDiabetesReport(Patient patient, List<Note> noteList);
}
