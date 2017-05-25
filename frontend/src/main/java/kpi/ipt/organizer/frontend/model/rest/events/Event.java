package kpi.ipt.organizer.frontend.model.rest.events;

import kpi.ipt.organizer.color.ColorUtils;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Event extends CreateEventRequest {

    private long userId;
    private String id;

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
