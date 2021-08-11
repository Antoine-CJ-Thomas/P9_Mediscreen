package patientReport.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is used to extract medical trigger term from a text file
 */
@Component
public class FileLineReader implements FileLineReaderInterface {

    private Logger logger = LogManager.getLogger(getClass().getSimpleName());

    private String filepath = "triggerTerm.txt";
//    private String filepath = "build/resources/main/triggerTerm.txt";

    /**
     * Creates a new FileLineReader
     */
    public FileLineReader() {
        logger.info("FileLineReader()");

    }

    @Override
    public List<String> getTriggerTermList() {
        logger.info("getTriggerTermList()");

        Scanner scanner;

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

                logger.info(e);

                e.printStackTrace();

            } finally {

                if (reader != null) {

                    try {

                        reader.close();

                    } catch (IOException e) {

                        logger.info(e);

                        e.printStackTrace();

                    }
                }
            }
        }

        return result;
    }
}