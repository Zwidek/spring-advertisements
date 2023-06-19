package pl.pb.ogloszeniadrobne.util.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.pb.ogloszeniadrobne.dto.UserRegistrationDto;
import pl.pb.ogloszeniadrobne.model.User;
import pl.pb.ogloszeniadrobne.repository.UserRepository;

import java.util.Optional;

@Component
public class RegistrationValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegistrationDto registrationDto = (UserRegistrationDto) target;
        String email = registrationDto.getEmail();
        String userName = registrationDto.getUsername();
        Optional<User> userByEmail = userRepository.findByEmail(email);
        Optional<User> userByUsername = userRepository.findByUsername(userName);
        if(userByEmail.isPresent()) {
            errors.rejectValue("email", "email.exists", "Email already exists");
        }
        if (userByUsername.isPresent()){
            errors.rejectValue("userName", "userName.exists", "userName already exists");
        }
    }
}
