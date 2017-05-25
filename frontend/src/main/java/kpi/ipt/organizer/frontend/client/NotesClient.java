package kpi.ipt.organizer.frontend.client;

import kpi.ipt.organizer.frontend.model.rest.notes.Note;
import kpi.ipt.organizer.frontend.model.rest.notes.NoteRequest;
import kpi.ipt.organizer.frontend.model.rest.notes.SearchRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@FeignClient(name = "notes-service")
@RequestMapping("/notes")
public interface NotesClient {

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    List<Note> getUserNotes(@PathVariable("userId") long userId);

    @RequestMapping(path = "/{userId}/search", method = RequestMethod.POST)
    List<Note> searchNotes(
            @PathVariable("userId") long userId,
            @RequestBody SearchRequest request
    );

    @RequestMapping(path = "/{userId}/{noteId}", method = RequestMethod.GET)
    Note getNote(
            @PathVariable("userId") long userId,
            @PathVariable("noteId") String noteId
    );

    @RequestMapping(path = "/{userId}", method = RequestMethod.POST)
    Note createNote(
            @PathVariable("userId") long userId,
            @RequestBody NoteRequest note
    );

    @RequestMapping(path = "/{userId}/{noteId}", method = RequestMethod.PUT)
    Map<String, Boolean> editNote(
            @PathVariable("userId") long userId,
            @PathVariable("noteId") String noteId,
            @RequestBody NoteRequest note
    );

    @RequestMapping(path = "/{userId}/{noteId}", method = RequestMethod.DELETE)
    Map<String, Boolean> deleteNote(
            @PathVariable("userId") long userId,
            @PathVariable("noteId") String noteId
    );
}
