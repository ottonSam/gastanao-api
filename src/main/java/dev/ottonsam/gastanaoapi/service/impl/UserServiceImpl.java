package dev.ottonsam.gastanaoapi.service.impl;

import dev.ottonsam.gastanaoapi.domain.User.UserRequestDTO;
import dev.ottonsam.gastanaoapi.domain.User.UserResponseDTO;
import dev.ottonsam.gastanaoapi.domain.User.User;
import dev.ottonsam.gastanaoapi.domain.repository.UserRepository;
import dev.ottonsam.gastanaoapi.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO findById(String id) {
        var user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return new UserResponseDTO(user);
    }

    @Override
    public UserResponseDTO create(UserRequestDTO userDTO) {
        if (userRepository.existsByLogin(userDTO.login())) {
            throw new IllegalArgumentException("This login already exists.");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());

        var user = userRepository.save(new User(new UserRequestDTO(userDTO.name(), userDTO.login(), encryptedPassword)));
        return new UserResponseDTO(user);
    }

    @Override
    public UserResponseDTO update(String id, UserRequestDTO user) {
        var userToUpdate = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (!userToUpdate.getLogin().equals(user.login()) && userRepository.existsByLogin(user.login())) {
            throw new IllegalArgumentException("This login already exists.");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.password());

        userToUpdate.setLogin(user.login());
        userToUpdate.setName(user.name());
        userToUpdate.setPassword(encryptedPassword);
        userRepository.save(userToUpdate);
        return new UserResponseDTO(userToUpdate);
    }

    @Override
    public void delete(String id) {
        var userToDelete = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        userRepository.delete(userToDelete);
    }

}
