package kpi.ipt.organizer.frontend.model.ui.notes;


import kpi.ipt.organizer.color.Color;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NoteCreateModel {

    protected String title;
    protected String description;
    protected String color;
}