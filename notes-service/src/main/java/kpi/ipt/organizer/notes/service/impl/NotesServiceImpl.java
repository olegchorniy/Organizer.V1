package kpi.ipt.organizer.notes.service.impl;

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
        int updatedNotes = notesRepository.updateByIdAndUserId(new Note(noteId, userId, noteProperties));

        return updatedNotes != 0;
    }

    @Override
    public boolean deleteNote(long userId, String noteId) {
        int deletedNotes = notesRepository.deleteByIdAndUserId(userId, noteId);

        return deletedNotes != 0;
    }
}
