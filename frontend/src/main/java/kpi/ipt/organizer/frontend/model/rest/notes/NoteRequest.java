package kpi.ipt.organizer.frontend.model.rest.notes;

import kpi.ipt.organizer.color.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequest {

    protected String title;
    protected String description;
    protected List<String> tags;
    protected Color color;
}