package ru.skypro.homework.filter;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.IncorrectUsernameException;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;
import java.util.Optional;

//@Component
@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    //    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Optional<User> byEmail = userRepository.findByEmail(userName);
//        if (byEmail.isEmpty()) {
//            throw new UsernameNotFoundException("Пользователь не найден");
//        }
//        User user = byEmail.get();
//        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), List.of(new SimpleGrantedAuthority(Role.USER.name())));
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.getUserByEmailIgnoreCase(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority(Role.USER.name())));
        } else {
            throw new UsernameNotFoundException("Пользователь : " + username + " не найден!");
        }
    }

    public void createUser(Register register) {
        if (userRepository.findUserByEmail(register.getUsername()).isPresent()) {
            throw new IncorrectUsernameException();
        }
        User user = new User();
        user.setEmail(register.getUsername());
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        user.setRole(Role.USER);
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());
        log.info("Метод: createUser. Перед сохранением в БД");
        log.info(String.valueOf(user));
        userRepository.save(user);
    }
}
