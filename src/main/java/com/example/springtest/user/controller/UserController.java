package com.example.springtest.user.controller;

import com.example.springtest.user.dto.UserEmailRequest;
import com.example.springtest.user.dto.UserInfoResponse;
import com.example.springtest.user.dto.UserListResponse;
import com.example.springtest.user.dto.UserSaveRequest;
import com.example.springtest.user.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private static final String HOME = "/";

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<Void> saveUser(@RequestBody UserSaveRequest request) {
        userService.save(request);
        return ResponseEntity.created(URI.create(HOME)).build();
    }

    @GetMapping
    public ResponseEntity<UserInfoResponse> findUserByEmail(@RequestBody UserEmailRequest request) {
        UserInfoResponse response = userService.findByEmail(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<UserListResponse> findUsers() {
        UserListResponse response = userService.findAll();
        return ResponseEntity.ok(response);
    }

}
