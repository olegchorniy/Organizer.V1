package kpi.ipt.organizer.notes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteProperties {

    protected String title;
    protected String description;
    protected List<String> tags;
    protected Color color;

    public NoteProperties(NoteProperties source) {
        this.title = source.title;
        this.description = source.description;
        this.tags = source.tags;
        this.color = source.color;
    }
}