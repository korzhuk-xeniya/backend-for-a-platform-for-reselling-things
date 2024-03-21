package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.filter.SecurityUserService;
import ru.skypro.homework.service.AuthService;

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

//    @Override
//    public boolean register(Register register) {
//        if (manager.userExists(register.getUsername())) {
//            return false;
//        }
//        manager.createUser(
//                User.builder()
//                        .passwordEncoder(this.encoder::encode)
//                        .password(register.getPassword())
//                        .username(register.getUsername())
//                        .roles(register.getRole().name())
//                        .build());
//        return true;
//    }

}
