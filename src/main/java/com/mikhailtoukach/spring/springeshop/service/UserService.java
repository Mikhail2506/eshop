package com.mikhailtoukach.spring.springeshop.service;


import com.mikhailtoukach.spring.springeshop.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    //security
    boolean save(UserDTO userDTO);

    List<UserDTO> getAll();
}
