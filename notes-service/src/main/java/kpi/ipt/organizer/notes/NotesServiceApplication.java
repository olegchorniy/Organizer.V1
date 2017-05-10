package kpi.ipt.organizer.notes;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotesServiceApplication {

    public static void main(String[] args) throws JsonProcessingException {
        //Temporary mongod running command: mongod --port 27111 --dbpath D:\servers\mongodb_data_directory\notes_db_sa
        SpringApplication.run(NotesServiceApplication.class, args);
    }
}
