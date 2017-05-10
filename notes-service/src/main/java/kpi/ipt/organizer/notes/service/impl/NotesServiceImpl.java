package kpi.ipt.organizer.notes.service.impl;

import kpi.ipt.organizer.auth.AuthenticationException;
import kpi.ipt.organizer.notes.model.Note;
import kpi.ipt.organizer.notes.model.NoteProperties;
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
        return notesRepository.findByIdAndUserId(userId, noteId);
    }

    @Override
    public String createNote(long userId, NoteProperties noteProperties) {
        Note createdNote = notesRepository.insert(new Note(null, userId, noteProperties));

        return createdNote.getId();
    }

    @Override
    public boolean updateNote(long userId, String noteId, NoteProperties noteProperties) {
        long updatedNotes = notesRepository.updateByIdAndUserId(new Note(noteId, userId, noteProperties));

        return updatedNotes != 0;
    }

    @Override
    public boolean deleteNote(long userId, String noteId) {
        long deletedNotes = notesRepository.deleteByIdAndUserId(noteId, userId);

        return deletedNotes != 0;
    }

    private static void checkPermissions(Note note, long userId) {
        if (note.getUserId() != userId) {
            throw new AuthenticationException(authErrorMessage(note.getId(), userId));
        }
    }

    private static String authErrorMessage(String noteId, long userId) {
        return String.format("User[id=%d] has no permission on NoteRequest[id=%s]", userId, noteId);
    }
}
