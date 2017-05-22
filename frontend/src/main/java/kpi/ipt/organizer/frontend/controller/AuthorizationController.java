package kpi.ipt.organizer.frontend.controller;

import kpi.ipt.organizer.frontend.exceptions.BadCredentialsException;
import kpi.ipt.organizer.frontend.model.rest.users.AuthRequest;
import kpi.ipt.organizer.frontend.model.rest.users.AuthResponse;
import kpi.ipt.organizer.frontend.model.rest.users.User;
import kpi.ipt.organizer.frontend.security.AuthenticationUtil;
import kpi.ipt.organizer.frontend.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthorizationController {

    private final UsersService usersService;

    public AuthorizationController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = usersService.login(authRequest.getEmail(), authRequest.getPassword());

        if (!authResponse.isAuthenticated()) {
            throw new BadCredentialsException();
        }

        User user = authResponse.getUser();
        AuthenticationUtil.login(user);

        return "redirect:/events?userId=" + user.getId();
    }

    @GetMapping("/logout")
    public String logout() {
        AuthenticationUtil.logout();

        return "redirect:/auth/login";
    }
}