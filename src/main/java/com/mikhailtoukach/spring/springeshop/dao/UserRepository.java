package com.mikhailtoukach.spring.springeshop.dao;

import com.mikhailtoukach.spring.springeshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByName (String name);
}
