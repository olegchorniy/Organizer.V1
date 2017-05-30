package kpi.ipt.organizer.frontend.model.rest.events;

import kpi.ipt.organizer.color.Color;
import kpi.ipt.organizer.color.ColorUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class Event extends CreateEventRequest {

    private long userId;
    private String id;

    public Event(long userId, String id, String title, Date start, Date end, Color color) {
        super(title, start, end, color);
        this.userId = userId;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Event(" +
                "userId=" + userId +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", color=" + ColorUtils.toWebString(color) +
                ")";
    }
}
