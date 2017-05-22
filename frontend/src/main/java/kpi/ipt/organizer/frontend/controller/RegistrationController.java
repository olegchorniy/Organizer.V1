package kpi.ipt.organizer.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @GetMapping
    public String register() {
        return "registration";
    }

    @PostMapping
    public String register(@RequestBody Map regRequest) {
        System.out.println(regRequest);
        return "registration";
    }
}
