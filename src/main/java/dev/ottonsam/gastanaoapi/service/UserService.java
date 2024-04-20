package dev.ottonsam.gastanaoapi.service;

import dev.ottonsam.gastanaoapi.domain.User.UserRequestDTO;
import dev.ottonsam.gastanaoapi.domain.User.UserResponseDTO;


public interface UserService {
    UserResponseDTO findById(String id);

    UserResponseDTO create(UserRequestDTO user);

    UserResponseDTO update(String id, UserRequestDTO user);

    void delete(String id);
}
