package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AdsMapperTest {
    private final AdsMapper adsMapper = Mappers.getMapper(AdsMapper.class);

    @Test
    void adsToAdsDto_shouldMapAdsToAdDto() {
        //given
        User testUser = new User();
        testUser.setId(50);
        Ads ads = new Ads();
        ads.setId(60);
        ads.setPrice(500);
        ads.setTitle("Test ads");
        ads.setUser(testUser);

        //when
        AdDto adDto = adsMapper.adsToAdsDto(ads);
        System.out.println(adDto);

        //then
        assertThat(adDto).isNotNull();
        assertThat(adDto.getAuthor()).isEqualTo(testUser.getId().intValue());
//        assertThat(adDto.getImage()).isNull();
        assertThat(adDto.getPk()).isEqualTo(ads.getId().intValue());
        assertThat(adDto.getPrice()).isEqualTo(ads.getPrice().intValue());
        assertThat(adDto.getTitle()).isEqualTo(ads.getTitle());
    }

    @Test
    void listAdsToAdsDto_shouldMapAdsListToAdsDto() {
        //given
        User testUser = new User();
        testUser.setId(55);
        Ads ads1 = new Ads();
        ads1.setId(1);
        ads1.setPrice(200);
        ads1.setTitle("Test ads");
        ads1.setUser(testUser);
        Ads ads2 = new Ads();
        ads2.setId(2);
        ads2.setPrice(300);
        ads2.setTitle("Test ads 2");
        ads2.setUser(testUser);
        List<Ads> adsList = List.of(ads1, ads2);

        //when
        AdsDto adsDto = adsMapper.listAdsToAdsDto(adsList.size(), adsList);
        System.out.println(adsDto);

        //then
        assertThat(adsDto).isNotNull();
        assertThat(adsDto.getCount()).isEqualTo(adsList.size());
        assertThat(adsDto.getResults()).isNotNull();
        assertThat(adsDto.getResults().get(0)).isEqualTo(adsMapper.adsToAdsDto(ads1));
    }

    @Test
    void createOrUpdateAdDtoToAds() {
    }

    @Test
    void toExtendedAdDto() {
    }

    @Test
    void updateAdsFromCreateOrUpdateAdDto() {
    }
}