package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class NewPasswordDto {

    private String currentPassword;

    private String newPassword;

    public NewPasswordDto currentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
        return this;
    }
}

