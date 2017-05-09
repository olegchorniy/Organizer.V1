package kpi.ipt.organizer.notes.web;

import kpi.ipt.organizer.auth.AuthUtils;
import kpi.ipt.organizer.notes.model.Note;
import kpi.ipt.organizer.notes.repository.NotesRepository;
import kpi.ipt.organizer.notes.service.NotesService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notes")
public class NotesController {

    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<Note> getUserNotes() {
        long userId = AuthUtils.currentUserId();

        return notesService.getUserNotes(userId);
    }

    @RequestMapping(path = "/{noteId}", method = RequestMethod.GET)
    public Note getNote(@PathVariable("noteId") String noteId) {
        long userId = AuthUtils.currentUserId();

        return notesService.getNote(userId, noteId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Map<String, String> createNote(@RequestBody Note note) {
        long userId = AuthUtils.currentUserId();

        Note createdNote = notesService.createNote(userId, note);

        return Collections.singletonMap("noteId", createdNote.getId());
    }

    @RequestMapping(path = "/{noteId}", method = RequestMethod.PUT)
    public SuccessResponse editNote(@PathVariable("noteId") String noteId, @RequestBody Note note) {
        long userId = AuthUtils.currentUserId();

        note.setId(noteId);
        notesService.updateNote(userId, note);

        return SuccessResponse.INSTANCE;
    }

    @RequestMapping(path = "/{noteId}", method = RequestMethod.DELETE)
    public SuccessResponse deleteNote(@PathVariable("noteId") String noteId) {
        long userId = AuthUtils.currentUserId();

        notesService.deleteNote(userId, noteId);

        return SuccessResponse.INSTANCE;
    }

    @Getter
    private static class SuccessResponse {

        private static final SuccessResponse INSTANCE = new SuccessResponse();

        private final boolean success = true;
    }

    /* ************************ Testing methods ************************ */

    @Autowired
    private NotesRepository notesRepository;

    @RequestMapping(path = "/test/getAll", method = RequestMethod.GET)
    public List<Note> getAllNotes() {
        return notesRepository.findAll();
    }

    @RequestMapping(path = "/test/{noteId}", method = RequestMethod.DELETE)
    public SuccessResponse deleteNoteById(@PathVariable("noteId") String noteId) {
        notesRepository.delete(noteId);
        return SuccessResponse.INSTANCE;
    }
}
