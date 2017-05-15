package kpi.ipt.organizer.events.repository;

import kpi.ipt.organizer.events.model.Event;

import java.util.List;

public interface EventsRepository {

    Event insert(Event event);

    int deleteByIdAndUserId(long userId, String eventId);

    List<Event> findAllByUserIdOrderByStartTime(long userId);
}
