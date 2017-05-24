package kpi.ipt.organizer.frontend.service.impl;

import kpi.ipt.organizer.color.Color;
import kpi.ipt.organizer.frontend.client.EventsClient;
import kpi.ipt.organizer.frontend.model.rest.events.CreateEventRequest;
import kpi.ipt.organizer.frontend.model.rest.events.CreateEventResponse;
import kpi.ipt.organizer.frontend.model.rest.events.Event;
import kpi.ipt.organizer.frontend.service.EventsService;
import kpi.ipt.organizer.frontend.utils.ConversionUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventsServiceImpl implements EventsService {

    private final EventsClient eventsClient;

    public EventsServiceImpl(EventsClient eventsClient) {
        this.eventsClient = eventsClient;
    }

    @Override
    public List<Event> getUserEvents(long userId, Date from, Date to) {
        Long fromTimestamp = ConversionUtils.dateToTimestamp(from);
        Long toTimestamp = ConversionUtils.dateToTimestamp(to);

        return eventsClient.getUserEvents(userId, fromTimestamp, toTimestamp);
    }

    @Override
    public String createEvent(long userId, String title, Date start, Color color) {
        CreateEventRequest eventRequest = new CreateEventRequest(title, start, start, color);
        CreateEventResponse response = eventsClient.createEvent(userId, eventRequest);

        return response.getEventId();
    }

    @Override
    public void removeEvent(long userId, String eventId) {
        eventsClient.deleteEvent(userId, eventId);
    }
}
