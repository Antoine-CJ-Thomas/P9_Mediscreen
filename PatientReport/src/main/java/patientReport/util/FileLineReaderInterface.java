package patientReport.util;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FileLineReaderInterface {

    List<String> getTriggerTermList();
}
