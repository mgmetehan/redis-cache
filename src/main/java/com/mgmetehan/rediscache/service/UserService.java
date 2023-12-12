package com.mgmetehan.rediscache.service;

import com.mgmetehan.rediscache.dto.CreateUserDto;
import com.mgmetehan.rediscache.model.User;
import com.mgmetehan.rediscache.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "usersCache")
public class UserService {
    private final UserRepository userRepository;

    @CacheEvict(cacheNames = "users", allEntries = true)
    public String createUser(CreateUserDto dto) {
        userRepository.save(dto.toEntity(dto));
        return "User created";
    }

    @Caching(evict = {@CacheEvict(cacheNames = "user", key = "#id"), @CacheEvict(cacheNames = "users", allEntries = true)})
    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

    @Cacheable(cacheNames = "users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Cacheable(cacheNames = "user", key = "#id", unless = "#result == null")
    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }
}
