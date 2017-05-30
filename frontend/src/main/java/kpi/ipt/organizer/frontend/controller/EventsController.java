package kpi.ipt.organizer.frontend.controller;

import kpi.ipt.organizer.color.ColorUtils;
import kpi.ipt.organizer.frontend.client.EventsClient;
import kpi.ipt.organizer.frontend.model.rest.events.CreateEventRequest;
import kpi.ipt.organizer.frontend.model.rest.events.CreateEventResponse;
import kpi.ipt.organizer.frontend.model.rest.events.Event;
import kpi.ipt.organizer.frontend.model.ui.events.EventCreateModel;
import kpi.ipt.organizer.frontend.model.ui.events.EventUiModel;
import kpi.ipt.organizer.frontend.security.AuthenticationUtil;
import kpi.ipt.organizer.frontend.utils.ConversionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventsController.class);

    private final EventsClient eventsClient;
    private final ConversionService conversionService;

    public EventsController(EventsClient eventsClient, ConversionService conversionService) {
        this.eventsClient = eventsClient;
        this.conversionService = conversionService;
    }

    @GetMapping
    public String events(Model model) {
        model.addAttribute("userName", AuthenticationUtil.getCurrentUser().getName());
        return "events";
    }

    @GetMapping("/load")
    @ResponseBody
    public List<EventUiModel> getEvents(
            @RequestParam(name = "start", required = false) Long start,
            @RequestParam(name = "end", required = false) Long end
    ) {
        long userId = AuthenticationUtil.getCurrentUser().getId();
        List<Event> events = eventsClient.getUserEvents(userId, start, end);

        return ConversionUtils.convert(conversionService, events, EventUiModel.class);
    }

    @PostMapping("/create")
    @ResponseBody
    public CreateEventResponse addEvent(@RequestBody EventCreateModel event) {
        LOGGER.info("Create event request: {}", event);

        long userId = AuthenticationUtil.getCurrentUser().getId();

        CreateEventRequest eventRequest = new CreateEventRequest(
                event.getTitle(),
                new Date(event.getStart()),
                ColorUtils.parseRgb(event.getColor())
        );

        return eventsClient.createEvent(userId, eventRequest);
    }

    @PostMapping("/remove")
    @ResponseStatus(HttpStatus.OK)
    public void removeEvent(@RequestParam("eventId") String eventId) {
        LOGGER.info("Remove event request: {}", eventId);

        long userId = AuthenticationUtil.getCurrentUser().getId();
        eventsClient.deleteEvent(userId, eventId);
    }
}
