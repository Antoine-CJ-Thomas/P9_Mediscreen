package patientReport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * This class is used to start the application
 */
@SpringBootApplication
@EnableFeignClients
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
