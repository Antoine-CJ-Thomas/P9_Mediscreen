package patientReport.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patientReport.model.DiabetesReport;
import patientReport.util.FileLineReader;
import patientReport.util.FileLineReaderInterface;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

/**
 * This class is used to process requests related to patient report
 */
@Service
public class DiabetesReportService implements DiabetesReportServiceInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    @Autowired
    private FileLineReaderInterface fileLineReaderInterface;

    /**
     * Creates a new DiabetesReportService
     */
    public DiabetesReportService() {
        logger.info("DiabetesReportService()");

        fileLineReaderInterface = new FileLineReader();
    }

    /**
     * Creates a new DiabetesReportService with the specified FileLineReaderInterface
     * @param fileLineReaderInterface : file reader that this service will use
     */
    public DiabetesReportService(FileLineReaderInterface fileLineReaderInterface) {
        logger.info("DiabetesReportService(" + fileLineReaderInterface + ")");

        this.fileLineReaderInterface = fileLineReaderInterface;
    }

    @Override
    public String assessDiabetes(DiabetesReport diabetesReport) {
        logger.info("assessDiabetes(" + diabetesReport + ")");

        String message;

        setGeneralTriggerTerm(diabetesReport);
        findMedicalTriggerTerm(diabetesReport);
        evaluateRiskLevel(diabetesReport);

        if (diabetesReport.getRiskLevel() != null) {

            message = "OK";
        }

        else {

            message = "Invalid informations";
        }

        return message;
    }

    /**
     * Write the general trigger term in the diabetes report
     * @param diabetesReport : diabetes report that will be completed
     */
    private void setGeneralTriggerTerm(DiabetesReport diabetesReport) {
        logger.info("setGeneralTriggerTerm(" + diabetesReport + ")");

        LocalDate birthDate =  Instant.ofEpochMilli(diabetesReport.getBirthDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();

        diabetesReport.setAge((int) java.time.temporal.ChronoUnit.YEARS.between(birthDate, today));

        if (diabetesReport.getAge() < 30) {

            diabetesReport.getGeneralTriggerTermList().add("A moins de 30ans");
        }

        else {

            diabetesReport.getGeneralTriggerTermList().add("A plus de 30ans");
        }

        if (diabetesReport.getGender().equals("F")) {

            diabetesReport.getGeneralTriggerTermList().add("Est une femme");
        }

        else if (diabetesReport.getGender().equals("M")) {

            diabetesReport.getGeneralTriggerTermList().add("Est un homme");
        }
    }

    /**
     * Write the medical trigger term in the diabetes report
     * @param diabetesReport : diabetes report that will be completed
     */
    private void findMedicalTriggerTerm(DiabetesReport diabetesReport) {
        logger.info("findMedicalTriggerTerm(" + diabetesReport + ")");

        List<String> triggerTermList = fileLineReaderInterface.getTriggerTermList();

        for (String c : diabetesReport.getCommentaryList()) {

            String lowerCommentary = c.toLowerCase(Locale.ROOT);

            for (String t : triggerTermList) {

                String lowerTriggerTerm = t.toLowerCase(Locale.ROOT);

                if (lowerCommentary.contains(lowerTriggerTerm) && !diabetesReport.getMedicalTriggerTermList().contains(t)) {

                    diabetesReport.getMedicalTriggerTermList().add(t);
                }
            }
        }
    }

    /**
     * Assess the risk level of contracting a diabetes
     * @param diabetesReport : diabetes report that will be completed
     */
    private void evaluateRiskLevel(DiabetesReport diabetesReport) {
        logger.info("evaluateRiskLevel(" + diabetesReport + ")");

        diabetesReport.setRiskLevel("Aucun risque");

        if (diabetesReport.getAge() < 30) {

            if (diabetesReport.getGender().equals("F")) {

                if (diabetesReport.getMedicalTriggerTermList().size() >= 4) {

                    diabetesReport.setRiskLevel("En Danger");
                }

                if (diabetesReport.getMedicalTriggerTermList().size() >= 7) {

                    diabetesReport.setRiskLevel("Apparition pr??coce");
                }
            }

            else if (diabetesReport.getGender().equals("M")) {

                if (diabetesReport.getMedicalTriggerTermList().size() >= 3) {

                    diabetesReport.setRiskLevel("En Danger");
                }

                if (diabetesReport.getMedicalTriggerTermList().size() >= 5) {

                    diabetesReport.setRiskLevel("Apparition pr??coce");
                }
            }
        }

        else if (diabetesReport.getAge() >= 30) {

            if (diabetesReport.getMedicalTriggerTermList().size() >= 2) {

                diabetesReport.setRiskLevel("Risque limit??");
            }

            if (diabetesReport.getMedicalTriggerTermList().size() >= 6) {

                diabetesReport.setRiskLevel("En Danger");
            }

            if (diabetesReport.getMedicalTriggerTermList().size() >= 8) {

                diabetesReport.setRiskLevel("Apparition pr??coce");
            }
        }
    }
}