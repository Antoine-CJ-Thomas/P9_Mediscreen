package userInterface.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userInterface.model.DiabetesReport;
import userInterface.model.Note;
import userInterface.model.Patient;
import userInterface.proxy.ReportProxy;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService implements ReportServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private ReportProxy reportProxy;

    public ReportService() {
        logger.info("ReportService()");

    }

    public ReportService(ReportProxy reportProxy) {
        logger.info("ReportService(" + reportProxy + ")");

        this.reportProxy = reportProxy;
    }

    @Override
    public DiabetesReport getDiabetesReport(Patient patient, List<Note> noteList) {
        logger.info("getDiabetesReport(" + patient + "," + noteList + ")");

        DiabetesReport diabetesReport = null;

        if (patient != null && noteList != null) {

            List<String> commentaryList = new ArrayList();

            for (Note n : noteList) {

                commentaryList.add(n.getCommentary());
            }

            diabetesReport = reportProxy.assessDiabetes(
                    new DiabetesReport(patient.getGender(), patient.getBirthDate(), commentaryList));
        }

        return diabetesReport;
    }
}