package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

public interface UserService {
    NewPasswordDto setPassword(NewPasswordDto newPassword, Authentication authentication);
    UserDto getAuthUserInfo();
    UpdateUserDto updateAuthUserInfo(UpdateUserDto updateUser);
    MultipartFile updateAvatar(MultipartFile avatar);

    String getImageByUserId(Integer id);

}
