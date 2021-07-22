package userInformation;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class allows to start the application
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws JsonProcessingException {

        SpringApplication.run(Application.class, args);
    }
}
