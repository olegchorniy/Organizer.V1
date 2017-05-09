package kpi.ipt.organizer.notes.service.impl;

import kpi.ipt.organizer.auth.AuthenticationException;
import kpi.ipt.organizer.notes.model.Note;
import kpi.ipt.organizer.notes.repository.NotesRepository;
import kpi.ipt.organizer.notes.service.NotesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServiceImpl implements NotesService {

    private final NotesRepository notesRepository;

    public NotesServiceImpl(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Override
    public List<Note> getUserNotes(long userId) {
        return notesRepository.findAllByUserIdOrderByCreationTimeDesc(userId);
    }

    @Override
    public Note getNote(long userId, String noteId) {
        Note note = notesRepository.findOne(noteId);
        if (note == null) {
            return null;
        }

        checkPermissions(note, userId);
        return note;
    }

    @Override
    public Note createNote(long userId, Note note) {
        note.setId(null);
        note.setUserId(userId);

        return notesRepository.insert(note);
    }

    @Override
    public void updateNote(long userId, Note note) {

    }

    @Override
    public void deleteNote(long userId, String noteId) {
        long deletedNotes = notesRepository.deleteByIdAndUserId(noteId, userId);

        if (deletedNotes == 0) {
            throw new AuthenticationException(authErrorMessage(noteId, userId));
        }
    }

    private static void checkPermissions(Note note, long userId) {
        if (note.getUserId() != userId) {
            throw new AuthenticationException(authErrorMessage(note.getId(), userId));
        }
    }

    private static String authErrorMessage(String noteId, long userId) {
        return String.format("User[id=%d] has no permission on Note[id=%s]", userId, noteId);
    }
}
