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

    private String title;
    private Date start;
    private Date end;
    private Color color;
}
