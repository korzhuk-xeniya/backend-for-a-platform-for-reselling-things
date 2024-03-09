package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

public interface UserService {
    String setPassword(NewPasswordDto newPassword);
    UserDto getAuthUserInfo();
    UpdateUserDto updateAuthUserInfo(UpdateUserDto updateUser);
    MultipartFile updateAvatar(MultipartFile avatar);

}
