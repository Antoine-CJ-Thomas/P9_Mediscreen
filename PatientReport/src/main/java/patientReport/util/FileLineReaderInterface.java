package patientReport.util;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This interface is used to implement file reader methods
 */
@Component
public interface FileLineReaderInterface {

    /**
     * Get the trigger term list extracted from a external file
     * @return The trigger term list found
     */
    List<String> getTriggerTermList();
}
