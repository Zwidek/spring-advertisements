package pl.pb.ogloszeniadrobne.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("isAuthenticated()")
class LoginController {
    @GetMapping("/login")
    String loginForm() {
        return "login-form";
    }
}