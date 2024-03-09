package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private HttpServletRequest request;
    private final ObjectMapper objectMapper;

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
    public ResponseEntity setPassword(@RequestBody NewPasswordDto newPassword) {
        String accept = request.getHeader("Accept");
        return ResponseEntity.ok(userService.setPassword(newPassword));
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
    public ResponseEntity<UserDto> getAuthUserInfo() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return ResponseEntity.ok(userService.getAuthUserInfo());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
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
    public ResponseEntity updateAuthUserInfo(@RequestBody UpdateUserDto updateUser) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return ResponseEntity.ok(userService.updateAuthUserInfo(updateUser));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
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
