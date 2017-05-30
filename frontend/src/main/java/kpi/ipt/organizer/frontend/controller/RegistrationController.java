package kpi.ipt.organizer.frontend.controller;

import kpi.ipt.organizer.frontend.client.UsersClient;
import kpi.ipt.organizer.frontend.model.rest.users.RegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    private final UsersClient usersClient;

    public RegistrationController(UsersClient usersClient) {
        this.usersClient = usersClient;
    }

    @GetMapping
    public String register() {
        return "registration";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody RegistrationRequest request) {
        LOGGER.info("Registration request: {}", request);

        usersClient.register(request);
    }
}
