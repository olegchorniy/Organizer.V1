package kpi.ipt.organizer.frontend.client;

import kpi.ipt.organizer.frontend.model.rest.users.AuthRequest;
import kpi.ipt.organizer.frontend.model.rest.users.AuthResponse;
import kpi.ipt.organizer.frontend.model.rest.users.RegistrationRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient("users-service")
@RequestMapping("/users")
public interface UsersClient {

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    Map<String, Boolean> register(@RequestBody RegistrationRequest request);

    @RequestMapping(path = "/authenticate", method = RequestMethod.POST)
    AuthResponse authenticate(@RequestBody AuthRequest authRequest);
}