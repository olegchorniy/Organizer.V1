package kpi.ipt.organizer.frontend.controller;

import kpi.ipt.organizer.frontend.security.AuthenticationUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/", "/index"})
    public String index() {
        return AuthenticationUtil.isAuthenticated()
                ? "redirect:/notes"
                : "redirect:/auth/login";
    }
}
