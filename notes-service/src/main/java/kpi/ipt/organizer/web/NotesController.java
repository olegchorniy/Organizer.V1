package kpi.ipt.organizer.web;

import kpi.ipt.organizer.auth.AuthUtils;
import kpi.ipt.organizer.model.Note;
import kpi.ipt.organizer.service.NotesService;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    //TODO: think more about security aspects, try to find similar examples

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
    public Note createNote(@RequestBody Note note) {
        note.setUserId(AuthUtils.currentUserId());

        return notesService.createNote(note);
    }

    @RequestMapping(path = "/{noteId}", method = RequestMethod.PUT)
    public SuccessResponse editNote(@PathVariable("noteId") String noteId, @RequestBody Note note) {

        note.setId(noteId);
        note.setUserId(AuthUtils.currentUserId());
        notesService.updateNote(note);

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
}
