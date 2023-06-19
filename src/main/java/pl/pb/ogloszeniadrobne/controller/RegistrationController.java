package pl.pb.ogloszeniadrobne.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pb.ogloszeniadrobne.dto.UserRegistrationDto;
import pl.pb.ogloszeniadrobne.service.UserService;
import pl.pb.ogloszeniadrobne.util.validator.RegistrationValidator;

@Controller
class RegistrationController {
    private final UserService userService;
    private final RegistrationValidator registrationValidator;

    public RegistrationController(UserService userService, RegistrationValidator registrationValidator) {
        this.userService = userService;
        this.registrationValidator = registrationValidator;
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        UserRegistrationDto user = new UserRegistrationDto();
        model.addAttribute("user", user);
        return "registration-form";
    }

    @PostMapping("/register")
    public String register(@Validated UserRegistrationDto userRegistrationDto, BindingResult bindingResult) {
        registrationValidator.validate(userRegistrationDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/form?error";
        }
        userService.register(userRegistrationDto);
        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String registrationConfirmation() {
        return "registration-confirmation";
    }
}