package kpi.ipt.organizer.web;

import kpi.ipt.organizer.model.request.CheckEmailRequest;
import kpi.ipt.organizer.model.request.RegistrationRequest;
import kpi.ipt.organizer.service.UsersService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public Map<String, Object> register(@RequestBody RegistrationRequest request) {
        usersService.registerUser(
                request.getEmail(),
                request.getPassword(),
                request.getName()
        );

        return Collections.singletonMap("success", true);
    }

    @RequestMapping(path = "/checkEmail", method = RequestMethod.POST)
    public Map<String, Object> checkEmail(@RequestBody CheckEmailRequest request) {
        boolean isEmailFree = usersService.checkEmail(request.getEmail());

        return Collections.singletonMap("free", isEmailFree);
    }
}
