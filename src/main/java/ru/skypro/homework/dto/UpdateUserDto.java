package ru.skypro.homework.dto;

public class UpdateUserDto {

    private String firstName;

    private String lastName;

    private String phone;

    public UpdateUserDto firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
}
