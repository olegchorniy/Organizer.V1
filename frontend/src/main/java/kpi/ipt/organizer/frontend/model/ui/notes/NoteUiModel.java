package kpi.ipt.organizer.frontend.model.ui.notes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NoteUiModel extends NoteCreateModel {

    private String noteId;

    public NoteUiModel(String noteId, String title, String description, String color) {
        super(title, description, color);
        this.noteId = noteId;
    }
}