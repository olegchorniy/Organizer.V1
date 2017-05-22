package kpi.ipt.organizer.users.web;

import com.fasterxml.jackson.annotation.JsonView;
import kpi.ipt.organizer.users.exceptions.UserNotFoundException;
import kpi.ipt.organizer.users.model.User;
import kpi.ipt.organizer.users.model.UserViews;
import kpi.ipt.organizer.users.model.request.AuthenticationRequest;
import kpi.ipt.organizer.users.model.request.CheckEmailRequest;
import kpi.ipt.organizer.users.model.request.RegistrationRequest;
import kpi.ipt.organizer.users.model.response.AuthenticationResponse;
import kpi.ipt.organizer.users.repository.UsersRepository;
import kpi.ipt.organizer.users.service.UsersService;
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

        usersService.registerUser(
                request.getEmail(),
                request.getPassword(),
                request.getName()
        );

        return Collections.singletonMap("success", true);
    }

    @RequestMapping(path = "/authenticate", method = RequestMethod.POST)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authRequest) {
        User user = usersService.authenticate(authRequest.getEmail(), authRequest.getPassword());

        return new AuthenticationResponse(user != null, user);
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

    @RequestMapping("/test/testEmail")
    public Map<String, Long> testEmail(@RequestBody String email) {
        long count = usersRepository.countUsersByEmail(email);

        return Collections.singletonMap("count", count);
    }
}
