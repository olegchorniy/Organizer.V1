package kpi.ipt.organizer.notes.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "notes")
public class Note {

    @Id
    private String id;

    private long userId;
    private String title;
    private String description;
    private List<String> tags;
    private Color color;
    private Date creationTime;
}
