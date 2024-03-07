package ru.skypro.homework.service;

import ru.skypro.homework.dto.Avatar;
import ru.skypro.homework.dto.Password;
import ru.skypro.homework.dto.User;

public interface UserService {
    String updatePassword(Password password);
    User getAuthUserInfo();
    User updateAuthUserInfo(User user);
    Avatar updateAvatar(String avatar);

}
