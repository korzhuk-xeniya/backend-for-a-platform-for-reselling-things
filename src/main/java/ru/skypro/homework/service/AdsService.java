package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ads;

import java.io.IOException;
import java.util.List;

public interface AdsService {
    List<Ads> getAllAds();

    Ads saveAd(CreateOrUpdateAdDto createOrUpdateAdDto, String name, MultipartFile file) throws IOException;

    Ads getAd(Integer id);

    boolean removeAd(String name, Integer id);

    Ads updateAds(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto, String name);
}
