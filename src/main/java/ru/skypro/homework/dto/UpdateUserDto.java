package ru.skypro.homework.dto;

import org.springframework.stereotype.Service;

@Service
public class UpdateUserDto {

    private String firstName;

    private String lastName;

    private String phone;

    public UpdateUserDto firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
}
