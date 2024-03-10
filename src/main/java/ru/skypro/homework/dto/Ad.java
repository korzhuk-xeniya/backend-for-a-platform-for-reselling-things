package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ad {
    private int adId;
    private String image;
    private int pk;
    private int price;
    private String title;
    private String description;

}
