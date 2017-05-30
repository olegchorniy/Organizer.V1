package kpi.ipt.organizer.frontend.controller;

import kpi.ipt.organizer.frontend.client.UsersClient;
import kpi.ipt.organizer.frontend.exceptions.BadCredentialsException;
import kpi.ipt.organizer.frontend.model.rest.users.AuthRequest;
import kpi.ipt.organizer.frontend.model.rest.users.AuthResponse;
import kpi.ipt.organizer.frontend.model.rest.users.User;
import kpi.ipt.organizer.frontend.security.AuthenticationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthorizationController {

    private final UsersClient usersClient;

    public AuthorizationController(UsersClient usersClient) {
        this.usersClient = usersClient;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = usersClient.authenticate(authRequest);

        if (!authResponse.isAuthenticated()) {
            throw new BadCredentialsException();
        }

        User user = authResponse.getUser();
        AuthenticationUtil.login(user);
    }

    @GetMapping("/logout")
    public String logout() {
        AuthenticationUtil.logout();

        return "redirect:/auth/login";
    }
}