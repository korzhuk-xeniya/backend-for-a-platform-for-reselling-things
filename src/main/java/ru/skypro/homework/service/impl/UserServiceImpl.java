package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.exception.WrongPasswordException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    @Override
    public NewPasswordDto setPassword(NewPasswordDto newPassword, Authentication authentication)  {
        User user = userRepository.findUserByEmail(SecurityContextHolder.getContext()
                .getAuthentication()
                .getName()).orElseThrow(() -> new UserNotFoundException());

        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            throw new WrongPasswordException();
        }

        user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);

        return newPassword;
    }

    @Override
    public User getAuthUserInfo(Authentication authentication) {
        User user = userRepository.findUserByEmail(SecurityContextHolder.getContext()
                .getAuthentication()
                .getName()).orElseThrow(() -> new UserNotFoundException());
        return user;
    }

    @Override
    public UpdateUserDto updateAuthUserInfo(UpdateUserDto updateUser, Authentication authenticatio) {
        User oldUser = userRepository.findUserByEmail(SecurityContextHolder.getContext()
                .getAuthentication()
                .getName()).orElseThrow(() -> new UserNotFoundException());

        oldUser.setFirstName(updateUser.getFirstName());
        oldUser.setLastName(updateUser.getLastName());
        oldUser.setPhone(updateUser.getPhone());

        UpdateUserDto newUser = userMapper.userToUpdateUserDto(oldUser);

        return newUser;
    }

    @Override
    public MultipartFile updateAvatar(MultipartFile avatar) {
        return null;
    }

    @Override
    public String getImageByUserId(Integer id) {
        User user = new User();
        String filePath = user.getAvatar().getFilePath();
        return filePath;
    }
}
