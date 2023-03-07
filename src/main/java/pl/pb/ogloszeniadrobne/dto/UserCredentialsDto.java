package pl.pb.ogloszeniadrobne.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@Getter
@Builder
public class UserCredentialsDto {

    private final String email;
    private final String password;
    private final Set<String> roles;
}