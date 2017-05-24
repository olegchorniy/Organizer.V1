package kpi.ipt.organizer.frontend.model.rest.events;

import kpi.ipt.organizer.color.Color;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class Event {

    private long userId;
    private String id;
    private String title;
    private Date start;
    private Date end;
    private Color color;
}
