package ru.skypro.homework.exception;

public class ImageNotFountException extends RuntimeException{
    public ImageNotFountException() {
        super("Image not found");
    }
}
