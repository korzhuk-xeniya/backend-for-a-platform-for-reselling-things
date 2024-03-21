package ru.skypro.homework.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.filter.SecurityUserService;
import ru.skypro.homework.service.AuthService;

@Log4j2
@Service
public class AuthServiceImpl implements AuthService {

    //    private final UserDetailsManager manager;
    private final SecurityUserService manager;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(SecurityUserService manager,
                           PasswordEncoder passwordEncoder) {
        this.manager = manager;
        this.encoder = passwordEncoder;
    }

    @Override
    public boolean login(String userName, String password) {

        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());

    }

    @Override
    public boolean register(Register register) {
        manager.createUser(register);
        log.info("Зарегистрирован новый пользователь: " + register.getUsername());
        return true;
    }
}
