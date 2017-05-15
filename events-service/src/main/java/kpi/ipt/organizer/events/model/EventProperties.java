package kpi.ipt.organizer.events.model;

import kpi.ipt.organizer.color.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventProperties {

    protected String title;
    protected Calendar start;
    protected Calendar end;
    protected Color color;

    public EventProperties(EventProperties source) {
        this.title = source.title;
        this.start = source.start;
        this.end = source.end;
        this.color = source.color;
    }
}
