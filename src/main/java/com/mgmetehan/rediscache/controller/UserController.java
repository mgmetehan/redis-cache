package com.mgmetehan.rediscache.controller;

import com.mgmetehan.rediscache.dto.CreateUserDto;
import com.mgmetehan.rediscache.model.User;
import com.mgmetehan.rediscache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    private ResponseEntity<String> createUser(@RequestBody CreateUserDto dto) {
        return ResponseEntity.ok(userService.createUser(dto));
    }

    @DeleteMapping()
    private ResponseEntity<String> deleteUser(@RequestParam String id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping()
    private ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
