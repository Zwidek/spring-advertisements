package pl.pb.ogloszeniadrobne.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {

    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone_number;

}