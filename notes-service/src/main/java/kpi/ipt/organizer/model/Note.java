package kpi.ipt.organizer.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "notes")
public class Note {

    @Id
    private String id;

    private long userId;
    private String title;
    private String description;
    private List<String> tags;
    private Color color;
    private LocalDateTime creationTime;
}
