package kpi.ipt.organizer.notes.service.impl;

import kpi.ipt.organizer.notes.model.Note;
import kpi.ipt.organizer.notes.model.NoteProperties;
import kpi.ipt.organizer.notes.repository.NotesRepository;
import kpi.ipt.organizer.notes.service.NotesService;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<Note> searchNotes(long userId, String searchPhrase) {
        return notesRepository.findAllByUserIdAndText(userId, searchPhrase);
    }

    @Override
    public Note getNote(long userId, String noteId) {
        return notesRepository.findByIdAndUserId(userId, noteId);
    }

    @Override
    public String createNote(long userId, NoteProperties noteProperties) {
        Note createdNote = notesRepository.insert(new Note(null, userId, new Date(), noteProperties));

        return createdNote.getId();
    }

    @Override
    public boolean updateNote(long userId, String noteId, NoteProperties noteProperties) {
        Note note = new Note(noteId, userId, null /* anyway creationTime is ignored upon update */, noteProperties);
        int updatedNotes = notesRepository.updateByIdAndUserId(note);

        return updatedNotes != 0;
    }

    @Override
    public boolean deleteNote(long userId, String noteId) {
        int deletedNotes = notesRepository.deleteByIdAndUserId(userId, noteId);

        return deletedNotes != 0;
    }
}
