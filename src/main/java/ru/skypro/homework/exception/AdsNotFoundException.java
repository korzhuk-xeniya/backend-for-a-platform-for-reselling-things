package ru.skypro.homework.exception;


public class AdsNotFoundException extends RuntimeException {
    public AdsNotFoundException() {
        super("Ad not found");
    }
}
