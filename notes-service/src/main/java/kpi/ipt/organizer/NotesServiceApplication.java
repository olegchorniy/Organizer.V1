package kpi.ipt.organizer;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesServiceApplication {

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(NotesServiceApplication.class, args);
    }
}
