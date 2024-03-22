package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Обновление пароля")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пароль обновлен",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(
                                    schema = @Schema(implementation = NewPasswordDto.class)))),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен")
    })
    @PostMapping("/set_password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity setPassword(@RequestBody NewPasswordDto newPassword, Authentication authentication) {

        log.info("Вызван метод контроллера для обновления пароля пользователя с login: {}", authentication.getName());
        userService.setPassword(newPassword, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация получена",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(
                                    schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public UserDto getAuthUserInfo(Authentication authentication) {

        log.info("Вызван метод контроллера для получения информации об авторизованном пользователе с login: {}", authentication.getName());
        User user = userService.getAuthUserInfo(authentication);
//        return ResponseEntity.ok(new UserDto());
        return userMapper.userToUserDto(user);

    }

    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация обновлена",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(
                                    schema = @Schema(implementation = UpdateUserDto.class)))),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    @PatchMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity updateAuthUserInfo(@RequestBody UpdateUserDto updateUser, Authentication authentication) {

        log.info("Вызван метод контроллера для изменения информации об авторизованном пользователе с login: {}", authentication.getName());
//        return ResponseEntity.ok(new UpdateUserDto());
        return ResponseEntity.ok(userService.updateAuthUserInfo(updateUser, authentication));
    }

    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Аватар успешно обновлен",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(
                                    schema = @Schema(implementation = MultipartFile.class)))),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    @PatchMapping("/me/image")
    public ResponseEntity updateAvatar(@RequestBody MultipartFile avatar, Authentication authentication) throws IOException {

        log.info("Вызван метод контроллера для обновления аватара");
        userService.updateAvatar(avatar, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/image/{id}")
    public String getImageByUserId(@PathVariable Integer userId) {

        log.info("Вызван метод контроллера для получения аватара пользователя с ID: {}", userId);

        return userService.getImageByUserId(userId);
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
