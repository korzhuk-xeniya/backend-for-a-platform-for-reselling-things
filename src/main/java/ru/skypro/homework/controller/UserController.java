package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Avatar;
import ru.skypro.homework.dto.Password;
import ru.skypro.homework.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private HttpServletRequest request;

    @PostMapping("/set_password")
    public ResponseEntity updatePassword(@RequestBody Password password) {
        return ResponseEntity.ok(userService.updatePassword(password));
    }

    @GetMapping("/me")
    public ResponseEntity getAuthUserInfo() {
        if (isUserAuthorized()) {
            return ResponseEntity.ok(userService.getAuthUserInfo());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PatchMapping("/me")
    public ResponseEntity updateAuthUserInfo(@RequestBody String firstName, String lastName, String phone) {
        return null;
    }
    @PatchMapping("/me/image")
    public ResponseEntity updateAvatar(@RequestBody Avatar avatar) {
        return null;
    }

    /**
     * Проверка является ли пользователь авторизованным
     */
    private boolean isUserAuthorized() {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("userId") != null) {
            return true;
        }
        return false;
    }
}
