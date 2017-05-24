package kpi.ipt.organizer.frontend.controller;

import kpi.ipt.organizer.frontend.model.rest.events.EventModel;
import kpi.ipt.organizer.frontend.model.ui.EventViewModel;
import kpi.ipt.organizer.frontend.security.AuthenticationUtil;
import kpi.ipt.organizer.frontend.service.EventsService;
import kpi.ipt.organizer.frontend.utils.ConversionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/events")
public class EventsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventsController.class);

    private final EventsService eventsService;
    private final ConversionService conversionService;

    public EventsController(EventsService eventsService, ConversionService conversionService) {
        this.eventsService = eventsService;
        this.conversionService = conversionService;
    }

    @GetMapping("/load")
    @ResponseBody
    public List<EventViewModel> getEvents() {
        long userId = AuthenticationUtil.getCurrentUser().getId();
        List<EventModel> events = eventsService.getUserEvents(userId, null, null);

        return ConversionUtils.convert(conversionService, events, EventViewModel.class);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void addEvent(@RequestBody Map request) {
        LOGGER.info("Create event request: {}", request);
    }

    @PostMapping("/remove")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEvent(@RequestBody Map request) {
        LOGGER.info("Delete event request: {}", request);
    }

    @GetMapping("/old")
    public String events(Model model) {
        long userId = AuthenticationUtil.getCurrentUser().getId();

        List<EventModel> events = eventsService.getUserEvents(userId, null, null);
        List<EventViewModel> eventViewModels = ConversionUtils.convert(conversionService, events, EventViewModel.class);

        model.addAttribute("events", eventViewModels);
        return "events";
    }
}
