package kpi.ipt.organizer.notes.repository;

import kpi.ipt.organizer.notes.model.Note;

import java.util.List;

public interface NotesRepository {

    Note findByIdAndUserId(long userId, String noteId);

    List<Note> findAllByUserIdOrderByCreationTimeDesc(long userId);

    Note insert(Note note);

    int updateByIdAndUserId(Note note);

    int deleteByIdAndUserId(long userId, String id);
}
