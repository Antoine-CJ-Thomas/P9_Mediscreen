package patientReport.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class FileLineReader implements FileLineReaderInterface {

    private String filepath = "PatientReport/src/main/resources/triggerTerm.txt";

    public FileLineReader() {

    }

    @Override
    public List<String> getTriggerTermList() {

        Scanner scanner = null;

        Reader reader = null;
        List<String> result = new ArrayList<>();

        if (filepath != null) {

            try {

                scanner = new Scanner(new FileInputStream(filepath),"UTF-8");

                while (scanner.hasNextLine()) {

                    result.add(scanner.nextLine());
                }

                scanner.close();

            } catch (IOException e) {

                e.printStackTrace();

            } finally {

                if (reader != null) {

                    try {

                        reader.close();

                    } catch (IOException e) {

                        e.printStackTrace();

                    }
                }
            }
        }

        return result;
    }
}
