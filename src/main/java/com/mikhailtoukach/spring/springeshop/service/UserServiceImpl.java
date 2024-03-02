package com.mikhailtoukach.spring.springeshop.service;

import com.mikhailtoukach.spring.springeshop.dao.UserRepository;
import com.mikhailtoukach.spring.springeshop.domain.Role;
import com.mikhailtoukach.spring.springeshop.domain.User;
import com.mikhailtoukach.spring.springeshop.dto.UserDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean save(@NotNull UserDTO userDTO) {
        if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            throw new RuntimeException("Password is not equals");
        }
        User user = User.builder()
                .name(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(Role.CLIENT)
                .build();

        userRepository.save(user);
        return true;
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findFirstByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Can't found user with name: " + username);
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));
        System.out.println(" ----------------------------");
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                roles);

//        return userRepository.findFirstByName(username)
//                .map(user -> new org.springframework.security.core.userdetails.User(
//                        user.getName(),
//                        user.getPassword(),
//                        Collections.singleton(user.getRole())
//                ))
//        .orElseThrow(()-> new UsernameNotFoundException("Failed to retrive use:" + username));
    }

    private UserDTO toDto(@NotNull User user) {
        return UserDTO.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();
    }

}
