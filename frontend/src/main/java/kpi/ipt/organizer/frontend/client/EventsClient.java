package kpi.ipt.organizer.frontend.client;

import kpi.ipt.organizer.frontend.model.rest.EventModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "events-service")
@RequestMapping("/events")
public interface EventsClient {

    @RequestMapping(path = "{userId}", method = RequestMethod.GET)
    List<EventModel> getUserEvents(
            @PathVariable("userId") long userId,
            @RequestParam(name = "from", required = false) Long from,
            @RequestParam(name = "to", required = false) Long to
    );
}
