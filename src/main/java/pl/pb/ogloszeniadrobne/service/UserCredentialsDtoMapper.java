package pl.pb.ogloszeniadrobne.service;

import lombok.NoArgsConstructor;
import pl.pb.ogloszeniadrobne.model.User;
import pl.pb.ogloszeniadrobne.dto.UserCredentialsDto;
import pl.pb.ogloszeniadrobne.model.UserRole;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
class UserCredentialsDtoMapper {

    static UserCredentialsDto map(User user) {
        return UserCredentialsDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().stream()
                        .map(UserRole::getName)
                        .collect(Collectors.toSet()))
                .build();
    }
}