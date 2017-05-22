package kpi.ipt.organizer.frontend.service.impl;

import kpi.ipt.organizer.frontend.client.EventsClient;
import kpi.ipt.organizer.frontend.model.rest.events.EventModel;
import kpi.ipt.organizer.frontend.service.EventsService;
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
    public List<EventModel> getUserEvents(long userId, Date from, Date to) {
        Long fromTimestamp = (from == null) ? null : from.getTime();
        Long toTimestamp = (to == null) ? null : to.getTime();

        return eventsClient.getUserEvents(userId, fromTimestamp, toTimestamp);
    }
}
