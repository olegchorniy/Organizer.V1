package kpi.ipt.organizer.notes.web;

import kpi.ipt.organizer.notes.exceptions.NoteNotFoundException;
import kpi.ipt.organizer.notes.model.Note;
import kpi.ipt.organizer.notes.model.NoteProperties;
import kpi.ipt.organizer.notes.model.request.SearchRequest;
import kpi.ipt.organizer.notes.service.NotesService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public List<Note> getUserNotes(@PathVariable("userId") long userId) {
        return notesService.getUserNotes(userId);
    }

    @RequestMapping(path = "/{userId}/search", method = RequestMethod.POST)
    public List<Note> searchNotes(
            @PathVariable("userId") long userId,
            @RequestBody SearchRequest request
    ) {
        return notesService.searchNotes(userId, request.getQuery());
    }

    @RequestMapping(path = "/{userId}/{noteId}", method = RequestMethod.GET)
    public Note getNote(
            @PathVariable("userId") long userId,
            @PathVariable("noteId") String noteId
    ) {
        Note note = notesService.getNote(userId, noteId);
        if (note == null) {
            throw new NoteNotFoundException();
        }

        return note;
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(
            @PathVariable("userId") long userId,
            @RequestBody NoteProperties noteProperties
    ) {
        return notesService.createNote(userId, noteProperties);
    }

    @RequestMapping(path = "/{userId}/{noteId}", method = RequestMethod.PUT)
    public SuccessResponse editNote(
            @PathVariable("userId") long userId,
            @PathVariable("noteId") String noteId,
            @RequestBody NoteProperties noteProperties
    ) {
        if (!notesService.updateNote(userId, noteId, noteProperties)) {
            throw new NoteNotFoundException();
        }

        return SuccessResponse.INSTANCE;
    }

    @RequestMapping(path = "/{userId}/{noteId}", method = RequestMethod.DELETE)
    public SuccessResponse deleteNote(
            @PathVariable("userId") long userId,
            @PathVariable("noteId") String noteId
    ) {
        if (!notesService.deleteNote(userId, noteId)) {
            throw new NoteNotFoundException();
        }

        return SuccessResponse.INSTANCE;
    }

    @Getter
    private static class SuccessResponse {

        private static final SuccessResponse INSTANCE = new SuccessResponse();

        private final boolean success = true;
    }

    /* ************************ Testing methods ************************ */

    @Autowired
    private NotesMongoRepository notesRepository;

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
