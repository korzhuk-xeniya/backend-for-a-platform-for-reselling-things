package ru.skypro.homework.service.impl;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

import java.nio.channels.MulticastChannel;

public class UserServiceImpl implements UserService {
    @Override
    public String setPassword(NewPasswordDto newPassword) {
        return null;
    }

    @Override
    public UserDto getAuthUserInfo() {
        return null;
    }

    @Override
    public UpdateUserDto updateAuthUserInfo(UpdateUserDto updateUser) {
        return null;
    }

    @Override
    public MultipartFile updateAvatar(MultipartFile avatar) {
        return null;
    }
}
