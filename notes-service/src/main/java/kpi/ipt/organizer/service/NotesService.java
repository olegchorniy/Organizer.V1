package kpi.ipt.organizer.service;

import kpi.ipt.organizer.model.Note;

import java.util.List;

public interface NotesService {

    List<Note> getUserNotes(long userId);

    Note getNote(long userId, String noteId);

    Note createNote(Note note);

    void updateNote(Note note);

    void deleteNote(long userId, String noteId);
}
