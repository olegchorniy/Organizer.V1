package kpi.ipt.organizer.frontend.controller;

import kpi.ipt.organizer.frontend.client.NotesClient;
import kpi.ipt.organizer.frontend.model.rest.notes.Note;
import kpi.ipt.organizer.frontend.model.ui.notes.NoteUiModel;
import kpi.ipt.organizer.frontend.security.AuthenticationUtil;
import kpi.ipt.organizer.frontend.utils.ConversionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public List<NoteUiModel> getUserNotes() {
        long userId = AuthenticationUtil.getCurrentUser().getId();
        List<Note> notes = notesClient.getUserNotes(userId);

        return ConversionUtils.convert(conversionService, notes, NoteUiModel.class);
    }
}
