package kpi.ipt.organizer.frontend.model.rest.notes;

import kpi.ipt.organizer.color.Color;
import kpi.ipt.organizer.color.ColorUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Note extends NoteRequest {

    private long userId;
    private String id;
    protected Date creationTime;

    public Note(long userId, String id, Date creationTime, String title, String description, List<String> tags, Color color) {

        super(title, description, tags, color);
        this.userId = userId;
        this.id = id;
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
