package br.com.ottonsam.gastanaoapi.controller;

import br.com.ottonsam.gastanaoapi.entity.User;
import br.com.ottonsam.gastanaoapi.entity.dtos.ResponseUserDto;
import br.com.ottonsam.gastanaoapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<ResponseUserDto> create(@RequestBody User user) {
        return ResponseEntity.ok(new ResponseUserDto(userService.create(user)));
    }

}
