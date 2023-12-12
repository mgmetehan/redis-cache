package com.mgmetehan.rediscache.service;

import com.mgmetehan.rediscache.dto.CreateUserDto;
import com.mgmetehan.rediscache.model.User;
import com.mgmetehan.rediscache.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String createUser(CreateUserDto dto) {
        userRepository.save(dto.toEntity(dto));
        return "User created";
    }

    public String deleteUser(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setDeleted(true);
            userRepository.save(user.get());
            return "User deleted";
        } else {
            return "User not found";
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
