package kpi.ipt.organizer.notes.service;

import kpi.ipt.organizer.notes.model.Note;
import kpi.ipt.organizer.notes.model.NoteProperties;

import java.util.List;

public interface NotesService {

    List<Note> getUserNotes(long userId);

    Note getNote(long userId, String noteId);

    /**
     * @return id of the created note
     */
    String createNote(long userId, NoteProperties noteProperties);

    /**
     * @return {@code true} if the note was successfully updated or {@code false} if the note wasn't found.
     */
    boolean updateNote(long userId, String noteId, NoteProperties noteProperties);

    /**
     * @return {@code true} if the note was successfully deleted or {@code false} if the note wasn't found.
     */
    boolean deleteNote(long userId, String noteId);
}
