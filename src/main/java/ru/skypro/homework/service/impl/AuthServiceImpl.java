package ru.skypro.homework.service.impl;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.filter.SecurityUserService;
import ru.skypro.homework.service.AuthService;

import java.util.List;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder encoder;
//    private final UserDetailsManager manager1;
    private final SecurityUserService manager;
    private final JdbcUserDetailsManager jdbcUserDetailsManager;


    public AuthServiceImpl(PasswordEncoder encoder, SecurityUserService manager, JdbcUserDetailsManager jdbcUserDetailsManager) {
        this.encoder = encoder;
        this.manager = manager;
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
    }
    @Getter
    private ru.skypro.homework.entity.User user;
    private Login authUserData;

    @Override
    public boolean login(String userName, String password) {

        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());

    }


    @Override
    public boolean register(Register register) {
        if (jdbcUserDetailsManager.userExists(register.getUsername())) {
            return false;
        }
        jdbcUserDetailsManager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(register.getPassword())
                        .username(register.getUsername())
                        .roles(register.getRole().name())
                        .build());
        return true;
    }

    public Login getLogin() {
        return authUserData;
    }





}
