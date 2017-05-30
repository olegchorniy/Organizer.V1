package kpi.ipt.organizer.frontend.model.rest.events;

import kpi.ipt.organizer.color.Color;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateEventRequest {

    protected String title;
    protected Date start;
    protected Date end;
    protected Color color;

    public CreateEventRequest(String title, Date start, Color color) {
        this.title = title;
        this.start = start;
        this.end = start;
        this.color = color;
    }
}
