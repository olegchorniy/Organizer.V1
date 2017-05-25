package kpi.ipt.organizer.frontend.controller;

import kpi.ipt.organizer.color.ColorUtils;
import kpi.ipt.organizer.frontend.model.rest.events.CreateEventResponse;
import kpi.ipt.organizer.frontend.model.rest.events.Event;
import kpi.ipt.organizer.frontend.model.ui.events.EventCreateModel;
import kpi.ipt.organizer.frontend.model.ui.events.EventUiModel;
import kpi.ipt.organizer.frontend.security.AuthenticationUtil;
import kpi.ipt.organizer.frontend.service.EventsService;
import kpi.ipt.organizer.frontend.utils.ConversionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    public List<EventUiModel> getEvents(
            @RequestParam(name = "start", required = false) Long start,
            @RequestParam(name = "end", required = false) Long end
    ) {
        long userId = AuthenticationUtil.getCurrentUser().getId();
        List<Event> events = eventsService.getUserEvents(
                userId,
                ConversionUtils.timestampToDate(start),
                ConversionUtils.timestampToDate(end)
        );

        return ConversionUtils.convert(conversionService, events, EventUiModel.class);
    }

    @PostMapping("/create")
    @ResponseBody
    public CreateEventResponse addEvent(@RequestBody EventCreateModel event) {
        LOGGER.info("Create event request: {}", event);

        long userId = AuthenticationUtil.getCurrentUser().getId();

        String eventId = eventsService.createEvent(
                userId,
                event.getTitle(),
                new Date(event.getStart()),
                ColorUtils.parseRgb(event.getColor())
        );

        return new CreateEventResponse(eventId);
    }

    @PostMapping("/remove")
    @ResponseStatus(HttpStatus.OK)
    public void removeEvent(@RequestParam("eventId") String eventId) {
        LOGGER.info("Remove event request: {}", eventId);

        long userId = AuthenticationUtil.getCurrentUser().getId();
        eventsService.removeEvent(userId, eventId);
    }
}
