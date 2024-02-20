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

import static com.mikhailtoukach.spring.springeshop.domain.Role.ADMIN;
import static com.mikhailtoukach.spring.springeshop.domain.Role.MANAGER;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
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
                        .requestMatchers("/users/new")
                        //.permitAll()
                        .hasRole(ADMIN.getAuthority())
                        //.hasAuthority(Role.ADMIN.getAuthority())

                        .requestMatchers("/", "/login", "v3/api-docs/", "/swagger-ui/")
                        .permitAll()

////                        .requestMatchers("/admin/**").hasRole(ADMIN.getAuthority())
//                        //.requestMatchers("/users/new").hasAuthority(Role.ADMIN.getAuthority())
//                        .requestMatchers(antMatcher("/user/{//d}/delete")).hasAnyAuthority(ADMIN.getAuthority(), MANAGER.getAuthority())
                        .anyRequest()
                        .permitAll())
                //.authenticated())
//                .httpBasic(Customizer.withDefaults());
                .formLogin(login -> login.loginPage("/login")
                        .loginProcessingUrl("/auth")
                        .defaultSuccessUrl("/users")
                        .failureUrl("/login-error")
                        .permitAll());
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/")
//                        .deleteCookies("JSESSIONID"));

        return http.build();
    }
}
