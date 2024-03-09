package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
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
    private final ObjectMapper objectMapper;

    @PostMapping("/set_password")
    public ResponseEntity setPassword(@RequestBody NewPasswordDto newPassword) {
        String accept = request.getHeader("Accept");
        return ResponseEntity.ok(userService.setPassword(newPassword));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getAuthUserInfo() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return ResponseEntity.ok(userService.getAuthUserInfo());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PatchMapping("/me")
    public ResponseEntity updateAuthUserInfo(@RequestBody UpdateUserDto updateUser) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return ResponseEntity.ok(userService.updateAuthUserInfo(updateUser));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

        @PatchMapping("/me/image")
    public ResponseEntity updateAvatar(@RequestBody MultipartFile avatar) {
        String accept = request.getHeader("Accept");
        return ResponseEntity.ok(userService.updateAvatar(avatar));
    }


    /**
     * Проверка является ли пользователь авторизованным
     */
//    private boolean isUserAuthorized() {
//        HttpSession session = request.getSession(false);
//
//        if (session != null && session.getAttribute("userId") != null) {
//            return true;
//        }
//        return false;
//    }

}
