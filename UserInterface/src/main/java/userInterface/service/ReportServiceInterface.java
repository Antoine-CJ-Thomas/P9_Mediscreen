package userInterface.service;

import org.springframework.stereotype.Component;
import userInterface.model.DiabetesReport;
import userInterface.model.Note;
import userInterface.model.Patient;

import java.util.List;

@Component
public interface ReportServiceInterface {

    DiabetesReport getDiabetesReport(Patient patient, List<Note> noteList);
}
