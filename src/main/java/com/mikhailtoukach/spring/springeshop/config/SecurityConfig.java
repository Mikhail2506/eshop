package com.mikhailtoukach.spring.springeshop.config;

import com.mikhailtoukach.spring.springeshop.domain.Role;
import com.mikhailtoukach.spring.springeshop.service.UserService;
import jakarta.persistence.Basic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled=true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Basic
    private AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users")
                        .hasAnyAuthority(Role.ADMIN.getAuthority(),Role.MANAGER.getAuthority())
                        //.permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/index").permitAll()
//                        .requestMatchers("/users/new")
//                        .hasAuthority(Role.ADMIN.getAuthority())
//                        добавили в @EnableMethodSecurity(... prePostEnabled = true, jsr250Enabled = true) доступ делаем
//                        @PreAuthorize в UserController class
                        .anyRequest()
                        .authenticated())

                //.httpBasic(Customizer.withDefaults())

                  //не работает, с default все работает
                .formLogin(login -> login
                        .loginPage("/login")//страница ввода логина и пароля
                        .defaultSuccessUrl("/users/new")//адрес,на который переходим после успешного ввода пароля логина
                        .failureUrl("/login-error")
                        .permitAll())// разрешаем тем, кто залогинился зайти дальше
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")//страница, на которую попадаем после logout
                        //.logoutSuccessUrl("/users")
                        .deleteCookies("JSESSIONID"))
        ;
        return http.build();
    }
}
