package com.mgmetehan.rediscache.repository;

import com.mgmetehan.rediscache.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
