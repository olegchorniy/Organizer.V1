package kpi.ipt.organizer.notes;

import com.fasterxml.jackson.core.JsonProcessingException;
import kpi.ipt.organizer.notes.model.Color;
import kpi.ipt.organizer.notes.model.Note;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class NotesServiceApplication {

    public static void main(String[] args) throws JsonProcessingException {
        //SpringApplication.run(NotesServiceApplication.class, args);

        System.out.println(new Note("123", 213, "asd", "asd", Arrays.asList("123", "123"), new Color(123), new Date()));
    }
}
