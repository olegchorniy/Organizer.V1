package kpi.ipt.organizer.notes.model;

import kpi.ipt.organizer.notes.utils.ColorUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = Note.COLLECTION_NAME)
public class Note extends NoteProperties {

    public static final String COLLECTION_NAME = "notes";

    @Id
    private String id;
    private long userId;
    protected Date creationTime;

    public Note(String id, long userId, Date creationTime, NoteProperties source) {
        super(source);

        this.id = id;
        this.userId = userId;
        this.creationTime = creationTime;
    }

    public Note(String id, long userId, Date creationTime,
                String title, String description, List<String> tags, Color color) {
        super(title, description, tags, color);

        this.id = id;
        this.userId = userId;
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Note(" +
                "id=" + id +
                ", userId=" + userId +
                ", title=" + title +
                ", description=" + description +
                ", tags=" + tags +
                ", color=" + ColorUtils.toWebString(color) +
                ", creationTime=" + creationTime +
                ")";
    }
}
