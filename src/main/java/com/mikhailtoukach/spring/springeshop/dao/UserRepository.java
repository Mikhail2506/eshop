package com.mikhailtoukach.spring.springeshop.dao;

import com.mikhailtoukach.spring.springeshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByName (String name);
}
