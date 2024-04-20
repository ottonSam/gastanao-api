package dev.ottonsam.gastanaoapi.domain.User;

import java.util.List;

public record UserResponseDTO(String id, String name, String login, String password, UserRole role) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getName(), user.getLogin(), user.getPassword(), user.getRule());
    }
}
