package com.chotonakib.mosqueservice.api.controller;

import com.chotonakib.mosqueservice.api.dto.response.UserInfoEntityDto;
import com.chotonakib.mosqueservice.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    ResponseEntity<Boolean> addUser(@RequestBody @Valid UserInfoEntityDto dto) {
        userService.addUser(dto);
        return ResponseEntity.ok(true);
    }
}
