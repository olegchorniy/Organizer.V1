package kpi.ipt.organizer.frontend.model.rest.notes;

import kpi.ipt.organizer.color.ColorUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Note extends NoteRequest {

    private long userId;
    private String id;
    protected Date creationTime;

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
