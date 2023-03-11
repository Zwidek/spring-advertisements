package pl.pb.ogloszeniadrobne.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.pb.ogloszeniadrobne.service.UserService;

@Controller
class ChangePasswordController {
    private final UserService userService;

    @Autowired
    public ChangePasswordController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/change-password")
    String changePassword(@RequestParam String newPassword,
                          RedirectAttributes redirectAttributes) {
        userService.changeCurrentUserPassword(newPassword);
        return "redirect:/logout";
    }
}