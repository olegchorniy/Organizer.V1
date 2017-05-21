package kpi.ipt.organizer.frontend.service;

import kpi.ipt.organizer.frontend.model.rest.EventModel;

import java.util.Date;
import java.util.List;

public interface EventsService {

    List<EventModel> getUserEvents(long userId, Date from, Date to);
}
