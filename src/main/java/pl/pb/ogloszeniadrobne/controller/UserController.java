package pl.pb.ogloszeniadrobne.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.pb.ogloszeniadrobne.model.User;
import pl.pb.ogloszeniadrobne.service.UserService;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Getter
@Setter
public class UserController {

    private UserService userService;

    @GetMapping("/user/{username}")
    private Optional<User> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}
