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

/**
 * This class is used to process requests related to patient report
 */
@Service
public class ReportService implements ReportServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private ReportProxy reportProxy;

    /**
     * Creates a new ReportService
     */
    public ReportService() {
        logger.info("ReportService()");

    }

    /**
     * Creates a new ReportService with the specified ReportProxy
     * @param reportProxy : proxy that this service will use
     */
    public ReportService(ReportProxy reportProxy) {
        logger.info("ReportService(" + reportProxy + ")");

        this.reportProxy = reportProxy;
    }

    @Override
    public DiabetesReport getDiabetesReport(Patient patient, List<Note> noteList) {
        logger.info("getDiabetesReport(" + patient + "," + noteList + ")");

        DiabetesReport diabetesReport = null;

        if (patient != null && noteList != null) {

            List<String> commentaryList = new ArrayList<>();

            for (Note n : noteList) {

                commentaryList.add(n.getCommentary());
            }

            diabetesReport = reportProxy.assessDiabetes(
                    new DiabetesReport(patient.getGender(), patient.getBirthDate(), commentaryList));
        }

        return diabetesReport;
    }
}