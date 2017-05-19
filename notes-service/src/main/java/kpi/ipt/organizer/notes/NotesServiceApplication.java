package kpi.ipt.organizer.notes;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotesServiceApplication {

    public static void main(String[] args) throws JsonProcessingException {
        SpringApplication.run(NotesServiceApplication.class, args);
    }
}
