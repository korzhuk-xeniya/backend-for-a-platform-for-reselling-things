package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.entity.Ads;

import java.util.List;

public interface AdsService {
    List<Ads> getAllAds();

    Ads saveAd(CreateOrUpdateAdDto createOrUpdateAdDto, String name, MultipartFile file);
}
