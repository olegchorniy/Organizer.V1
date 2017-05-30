package kpi.ipt.organizer.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FrontendApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(FrontendApplication.class);
        application.setAdditionalProfiles("production");
        //application.setAdditionalProfiles("local");
        application.run(args);
    }
}
