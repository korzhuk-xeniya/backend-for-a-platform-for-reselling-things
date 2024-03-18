package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserOrAdminService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdsServiceImpl implements AdsService {
    private final AdsRepository adsRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final  UserOrAdminService userOrAdminService;
    private final AdsMapper adsMapper;

    /**
     * @return список объявлений
     */
    @Override
    public List<Ads> getAllAds() {
        List<Ads> adsList = adsRepository.findAll();
        log.info(adsList.toString());
        return adsList;

    }

    /**
     * @param createOrUpdateAdDto заголовок, цена и описание
     * @param email электронная почта
     * @param imageFile файл с изображением
     * @return объявление
     */
    @Override
    public Ads saveAd(CreateOrUpdateAdDto createOrUpdateAdDto, String email, MultipartFile imageFile) throws IOException {
        log.info("запустился метод сохранения объявления");
        Ads saveAds = adsMapper.CreateOrUpdateAdDtoToAds(createOrUpdateAdDto);
        saveAds.setUser(userRepository.findUserByEmail(email).orElseThrow());
        Image image = imageService.saveImageFile(imageFile);
        saveAds.setImage(image);
        adsRepository.saveAndFlush(saveAds);
        return saveAds;
    }

    /**
     * @param id id объявления
     * @return объявление
     */
    @Override
    public Ads getAd(Integer id) {
        Ads ads = adsRepository.findById(id).orElseThrow();
        log.info(String.valueOf(ads));
        return ads;

    }

    @Override
    public boolean removeAd(String email, Integer id) {
        Ads ads = adsRepository.findById(id).orElseThrow();
        User adOwner = ads.getUser();
        if (userOrAdminService.isUserOrAdmin(email, adOwner)) {
            log.info("запустился метод удаления объявления");
            try {
                Files.deleteIfExists(Path.of(ads.getImage().getFilePath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            adsRepository.deleteById(id);
            return true;
        }
        return false;

    }

    @Override
    public Ads updateAds(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto, String name) {
        Optional<Ads> optionalAds = adsRepository.findById(id);
        if (optionalAds.isPresent()) {
            Ads ads = optionalAds.get();
            User adOwner = ads.getUser();
            if (userOrAdminService.isUserOrAdmin(name, adOwner)) {
                log.info("запустился метод обновления объявления");
                adsMapper.updateAdsFromCreateOrUpdateAdDto(createOrUpdateAdDto, ads);
                adsRepository.save(ads);
                return ads;
            }
        }
        return null;
    }
}
