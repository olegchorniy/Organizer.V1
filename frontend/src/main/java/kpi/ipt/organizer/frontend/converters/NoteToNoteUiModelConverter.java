package kpi.ipt.organizer.frontend.converters;

import kpi.ipt.organizer.color.ColorUtils;
import kpi.ipt.organizer.frontend.model.rest.notes.Note;
import kpi.ipt.organizer.frontend.model.ui.notes.NoteUiModel;
import org.springframework.core.convert.converter.Converter;

public class NoteToNoteUiModelConverter implements Converter<Note, NoteUiModel> {

    @Override
    public NoteUiModel convert(Note note) {
        return new NoteUiModel(
                note.getId(),
                note.getTitle(),
                note.getDescription(),
                ColorUtils.toWebString(note.getColor())
        );
    }
}