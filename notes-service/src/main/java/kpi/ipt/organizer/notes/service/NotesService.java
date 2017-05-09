package kpi.ipt.organizer.notes.service;

import kpi.ipt.organizer.notes.model.Note;

import java.util.List;

public interface NotesService {

    List<Note> getUserNotes(long userId);

    Note getNote(long userId, String noteId);

    Note createNote(long userId, Note note);

    void updateNote(long userId, Note note);

    void deleteNote(long userId, String noteId);
}
