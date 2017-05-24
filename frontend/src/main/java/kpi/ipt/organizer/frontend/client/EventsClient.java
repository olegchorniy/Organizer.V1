package kpi.ipt.organizer.frontend.client;

import kpi.ipt.organizer.frontend.model.rest.events.CreateEventRequest;
import kpi.ipt.organizer.frontend.model.rest.events.CreateEventResponse;
import kpi.ipt.organizer.frontend.model.rest.events.Event;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "events-service")
@RequestMapping("/events")
public interface EventsClient {

    @RequestMapping(path = "{userId}", method = RequestMethod.GET)
    List<Event> getUserEvents(
            @PathVariable("userId") long userId,
            @RequestParam(name = "from", required = false) Long from,
            @RequestParam(name = "to", required = false) Long to
    );

    @RequestMapping(path = "/{userId}/{eventId}", method = RequestMethod.DELETE)
    Map<String, Boolean> deleteEvent(
            @PathVariable("userId") long userId,
            @PathVariable("eventId") String eventId
    );

    @RequestMapping(path = "{userId}", method = RequestMethod.POST)
    CreateEventResponse createEvent(
            @PathVariable("userId") long userId,
            @RequestBody CreateEventRequest eventProperties
    );
}
