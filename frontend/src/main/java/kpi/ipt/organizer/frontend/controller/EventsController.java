package kpi.ipt.organizer.frontend.controller;

import kpi.ipt.organizer.frontend.model.rest.events.EventModel;
import kpi.ipt.organizer.frontend.model.ui.EventViewModel;
import kpi.ipt.organizer.frontend.service.EventsService;
import kpi.ipt.organizer.frontend.utils.ConversionUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventsController {

    private final EventsService eventsService;
    private final ConversionService conversionService;

    public EventsController(EventsService eventsService, ConversionService conversionService) {
        this.eventsService = eventsService;
        this.conversionService = conversionService;
    }

    @GetMapping
    public String events(@RequestParam("userId") long userId, Model model) {

        List<EventModel> events = eventsService.getUserEvents(userId, null, null);
        List<EventViewModel> eventViewModels = ConversionUtils.convert(conversionService, events, EventViewModel.class);

        model.addAttribute("events", eventViewModels);
        return "events";
    }
}
