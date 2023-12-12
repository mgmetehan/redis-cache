package com.mgmetehan.rediscache.service;

import com.mgmetehan.rediscache.dto.CreateUserDto;
import com.mgmetehan.rediscache.dto.UpdateUserDto;
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
    private final CacheService cacheService;

    @CacheEvict(value = "usersCache", allEntries = true, cacheManager = "cacheManager")
    public User createUser(CreateUserDto dto) {
        var user = userRepository.save(dto.toEntity(dto));
        //cacheService.deleteCache("usersCache::getUsers");
        return user;
    }

    @Caching(evict = {@CacheEvict(cacheNames = "usersCache", key = "#id", cacheManager = "cacheManager"),
            @CacheEvict(cacheNames = "usersCache", allEntries = true, cacheManager = "cacheManager")})
    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

    @Cacheable(value = "usersCache", key = "#root.methodName", unless = "#result == null", cacheManager = "cacheManager")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Cacheable(cacheNames = "usersCache", key = "#id", unless = "#result == null", cacheManager = "cacheManager")
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @CacheEvict(value = "usersCache", cacheManager = "cacheManager", allEntries = true)
    public String updateUser(UpdateUserDto dto) {
        Optional<User> user = userRepository.findById(dto.getId());
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setPassword(dto.getPassword());
            userRepository.save(user1);
            return "User updated";
        } else {
            return "User not found";
        }
    }
}
