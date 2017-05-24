package kpi.ipt.organizer.frontend.service;

import kpi.ipt.organizer.color.Color;
import kpi.ipt.organizer.frontend.model.rest.events.Event;

import java.util.Date;
import java.util.List;

public interface EventsService {

    List<Event> getUserEvents(long userId, Date from, Date to);

    String createEvent(long userId, String title, Date start, Color color);

    void removeEvent(long userId, String eventId);
}
