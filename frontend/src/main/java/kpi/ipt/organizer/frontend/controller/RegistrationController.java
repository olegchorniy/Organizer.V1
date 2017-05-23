package kpi.ipt.organizer.frontend.controller;

import kpi.ipt.organizer.frontend.model.rest.users.RegistrationRequest;
import kpi.ipt.organizer.frontend.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UsersService usersService;

    public RegistrationController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String register() {
        return "registration";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String register(@RequestBody RegistrationRequest request) {
        System.out.println(request);

        usersService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        return "registration";
    }
}
