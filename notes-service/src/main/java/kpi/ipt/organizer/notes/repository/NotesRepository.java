package kpi.ipt.organizer.notes.repository;

import kpi.ipt.organizer.notes.model.Note;

import java.util.List;

public interface NotesRepository {

    Note findByIdAndUserId(long userId, String noteId);

    List<Note> findAllByUserIdOrderByCreationTimeDesc(long userId);

    Note insert(Note note);

    long updateByIdAndUserId(Note note);

    long deleteByIdAndUserId(String id, long userId);
}
