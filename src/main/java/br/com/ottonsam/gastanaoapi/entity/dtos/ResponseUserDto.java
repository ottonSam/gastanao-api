package br.com.ottonsam.gastanaoapi.entity.dtos;

import br.com.ottonsam.gastanaoapi.entity.User;

public record ResponseUserDto(String username, String email) {
    public ResponseUserDto(User user) {
        this(user.getUsername(), user.getEmail());
    }
}
