package userInformation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import userInformation.model.User;

/**
 * This class allows to start the application
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(objectMapper.writeValueAsString(new User()));


        SpringApplication.run(Application.class, args);
    }
}
