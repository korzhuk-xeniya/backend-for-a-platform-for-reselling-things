package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import javax.validation.constraints.Max;

@Data
public class UserDto {

    @Schema(description = "id пользователя")
    @Max(32)
    private Integer id;

    @Schema(description = "логин пользователя")
    private String email;

    @Schema(description = "имя пользователя")
    private String firstName;

    @Schema(description = "фамилия пользователя")
    private String lastName;

    @Schema(description = "телефон пользователя")
    private String phone;

    @Schema(description = "роль пользователя")
    private Role role;

    @Schema(description = "ссылка на аватар пользователя")
    private String image;
}
