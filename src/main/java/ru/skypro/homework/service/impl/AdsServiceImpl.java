package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdsServiceImpl implements AdsService {
    private AdsRepository adsRepository;
    private UserRepository userRepository;
    private ImageService imageService;

    @Override
    public List<Ads> getAllAds() {
        List<Ads> adsList = adsRepository.findAll();
        log.info(adsList.toString());
        return adsList;

    }

    @Override
    public Ads saveAd(CreateOrUpdateAdDto createOrUpdateAdDto, String email, MultipartFile imageFile) {
        log.info("запустился метод saveAd.");
        Ads saveAds = AdsMapper.INSTANSE.adsDtoToAds(createOrUpdateAdDto);
        saveAds.setUser(userRepository.findUserByEmail(email).orElseThrow());
        Image image;
        try {
            image = imageService.saveImageFile(imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        saveAds.setImage(image);
        adsRepository.saveAndFlush(saveAds);
        return saveAds;

    }
}
