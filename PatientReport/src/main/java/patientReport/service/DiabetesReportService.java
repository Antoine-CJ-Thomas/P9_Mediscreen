package patientReport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patientReport.model.DiabetesReport;
import patientReport.util.FileLineReader;
import patientReport.util.FileLineReaderInterface;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

@Service
public class DiabetesReportService implements DiabetesReportServiceInterface {

    @Autowired
    private FileLineReaderInterface fileLineReaderInterface;

    public DiabetesReportService() {

        fileLineReaderInterface = new FileLineReader();
    }

    public DiabetesReportService(FileLineReaderInterface fileLineReaderInterface) {

        this.fileLineReaderInterface = fileLineReaderInterface;
    }

    @Override
    public void setGeneralTriggerTerm(DiabetesReport diabetesReport) {

        LocalDate birthDate = diabetesReport.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate today = LocalDate.now();

        diabetesReport.setAge((int) java.time.temporal.ChronoUnit.YEARS.between(birthDate, today));

        if (diabetesReport.getAge() >= 30) {

            diabetesReport.getGeneralTriggerTermList().add("A plus de 30ans");
        }

        else {

            diabetesReport.getGeneralTriggerTermList().add("A moins de 30ans");
        }

        if (diabetesReport.getGender().equals("M")) {

            diabetesReport.getGeneralTriggerTermList().add("Est un homme");
        }

        else if (diabetesReport.getGender().equals("F")) {

            diabetesReport.getGeneralTriggerTermList().add("Est une femme");
        }
    }

    @Override
    public void findMedicalTriggerTerm(DiabetesReport diabetesReport) {

        for (String c : diabetesReport.getCommentaryList()) {

            String lowerCommentary = c.toLowerCase(Locale.ROOT);

            for (String t : fileLineReaderInterface.getTriggerTermList()) {

                String lowerTriggerTerm = t.toLowerCase(Locale.ROOT);

                if (lowerCommentary.contains(lowerTriggerTerm) && !diabetesReport.getMedicalTriggerTermList().contains(t)) {

                    diabetesReport.getMedicalTriggerTermList().add(t);
                }
            }
        }
    }

    @Override
    public void evaluateRiskLevel(DiabetesReport diabetesReport) {

        diabetesReport.setRiskLevel("Aucun risque");

        if (diabetesReport.getAge() < 30) {

            if (diabetesReport.getGender() == "M") {

                if (diabetesReport.getMedicalTriggerTermList().size() >= 3) {

                    diabetesReport.setRiskLevel("En Danger");
                }

                if (diabetesReport.getMedicalTriggerTermList().size() >= 5) {

                    diabetesReport.setRiskLevel("Apparition précoce");
                }
            }

            if (diabetesReport.getGender() == "F") {

                if (diabetesReport.getMedicalTriggerTermList().size() >= 4) {

                    diabetesReport.setRiskLevel("En Danger");
                }

                if (diabetesReport.getMedicalTriggerTermList().size() >= 7) {

                    diabetesReport.setRiskLevel("Apparition précoce");
                }
            }
        }

        else if (diabetesReport.getAge() >= 30) {

            if (diabetesReport.getMedicalTriggerTermList().size() >= 2) {

                diabetesReport.setRiskLevel("Risque limité");
            }

            if (diabetesReport.getMedicalTriggerTermList().size() >= 6) {

                diabetesReport.setRiskLevel("En Danger");
            }

            if (diabetesReport.getMedicalTriggerTermList().size() >= 8) {

                diabetesReport.setRiskLevel("Apparition précoce");
            }
        }
    }
}