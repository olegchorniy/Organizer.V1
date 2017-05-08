package kpi.ipt.organizer.web;

import com.fasterxml.jackson.annotation.JsonView;
import kpi.ipt.organizer.exceptions.UserNotFoundException;
import kpi.ipt.organizer.model.User;
import kpi.ipt.organizer.model.UserViews;
import kpi.ipt.organizer.model.request.AuthenticationRequest;
import kpi.ipt.organizer.model.request.CheckEmailRequest;
import kpi.ipt.organizer.model.request.RegistrationRequest;
import kpi.ipt.organizer.repository.UsersRepository;
import kpi.ipt.organizer.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(path = "/checkEmail", method = RequestMethod.POST)
    public Map<String, Object> checkEmail(@RequestBody CheckEmailRequest request) {

        boolean isEmailFree = usersService.checkEmail(request.getEmail());

        return Collections.singletonMap("free", isEmailFree);
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public Map<String, Object> register(@RequestBody RegistrationRequest request) {

        usersService.createUser(
                request.getEmail(),
                request.getPassword(),
                request.getName()
        );

        return Collections.singletonMap("success", true);
    }

    @RequestMapping(path = "/authenticate", method = RequestMethod.POST)
    public Map<String, Object> authenticate(@RequestBody AuthenticationRequest authRequest) {
        boolean authenticated = usersService.authenticate(authRequest.getEmail(), authRequest.getPassword());

        return Collections.singletonMap("authenticated", authenticated);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    @JsonView(UserViews.Public.class)
    public User getUser(@PathVariable("userId") long userId) {

        User user = usersService.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    /* ********************** This code is needed only for testing purposes ************** */

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping("/test/allUsers")
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }
}
