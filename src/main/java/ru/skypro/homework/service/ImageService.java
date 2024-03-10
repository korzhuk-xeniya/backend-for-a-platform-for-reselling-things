package ru.skypro.homework.service;

import javax.servlet.http.HttpServletResponse;

public interface ImageService {
    void transferImageToResponse(Integer id, HttpServletResponse response);
}
