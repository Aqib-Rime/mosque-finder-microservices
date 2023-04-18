package com.chotonakib.mosqueservice.api.controller;

import com.chotonakib.mosqueservice.api.dto.response.UserInfoEntityDto;
import com.chotonakib.mosqueservice.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    ResponseEntity<Boolean> registerUser(@RequestBody @Valid UserInfoEntityDto dto) {
        userService.addUser(dto);
        return ResponseEntity.ok(true);
    }
}
