package kpi.ipt.organizer.frontend.controller;

import kpi.ipt.organizer.color.ColorUtils;
import kpi.ipt.organizer.frontend.client.NotesClient;
import kpi.ipt.organizer.frontend.model.rest.notes.Note;
import kpi.ipt.organizer.frontend.model.rest.notes.NoteRequest;
import kpi.ipt.organizer.frontend.model.rest.notes.SearchRequest;
import kpi.ipt.organizer.frontend.model.ui.notes.NoteCreateModel;
import kpi.ipt.organizer.frontend.model.ui.notes.NoteUiModel;
import kpi.ipt.organizer.frontend.model.ui.notes.NoteUpdateModel;
import kpi.ipt.organizer.frontend.security.AuthenticationUtil;
import kpi.ipt.organizer.frontend.utils.ConversionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotesController.class);

    private final NotesClient notesClient;
    private final ConversionService conversionService;

    public NotesController(NotesClient notesClient, ConversionService conversionService) {
        this.notesClient = notesClient;
        this.conversionService = conversionService;
    }

    @GetMapping("/load")
    @ResponseBody
    public List<NoteUiModel> getUserNotes(
            @RequestParam(name = "searchLine", required = false) String searchLine
    ) {
        LOGGER.info("Get notes request: searchLine = {}", searchLine);

        long userId = AuthenticationUtil.getCurrentUser().getId();
        List<Note> notes;

        if (!StringUtils.isEmpty(searchLine)) {
            notes = notesClient.searchNotes(userId, new SearchRequest(searchLine));
        } else {
            notes = notesClient.getUserNotes(userId);
        }

        return ConversionUtils.convert(conversionService, notes, NoteUiModel.class);
    }

    @ResponseBody
    @PostMapping("/create")
    public Map<String, String> createNode(@RequestBody NoteCreateModel note) {
        LOGGER.info("Create note request: note = {}", note);

        long userId = AuthenticationUtil.getCurrentUser().getId();

        NoteRequest noteRequest = new NoteRequest(
                note.getTitle(),
                note.getDescription(),
                Collections.emptyList(),
                ColorUtils.parseRgb(note.getColor())
        );

        Note createdNote = notesClient.createNote(userId, noteRequest);
        return Collections.singletonMap("noteId", createdNote.getId());
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateNode(@RequestBody NoteUpdateModel note) {
        LOGGER.info("Update note request: note = {}", note);

        long userId = AuthenticationUtil.getCurrentUser().getId();
        String noteId = note.getNoteId();

        NoteRequest noteRequest = new NoteRequest(
                note.getTitle(),
                note.getDescription(),
                Collections.emptyList(),
                ColorUtils.parseRgb(note.getColor())
        );

        notesClient.editNote(userId, noteId, noteRequest);
    }

    @PostMapping("/remove")
    @ResponseStatus(HttpStatus.OK)
    public void removeNode(@RequestParam("noteId") String noteId) {
        LOGGER.info("Remove note request: noteId = {}", noteId);

        long userId = AuthenticationUtil.getCurrentUser().getId();

        notesClient.deleteNote(userId, noteId);
    }
}
