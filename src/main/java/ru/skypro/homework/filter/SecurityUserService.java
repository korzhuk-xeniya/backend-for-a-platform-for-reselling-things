package ru.skypro.homework.filter;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {
    private final UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> byEmail = userRepository.findUserByEmail(userName);
        if (byEmail.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        User user = byEmail.get();
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), List.of(new SimpleGrantedAuthority(Role.USER.name())));
    }
}
