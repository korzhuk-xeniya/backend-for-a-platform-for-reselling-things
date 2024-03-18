package ru.skypro.homework.mapper;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.entity.Ads;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AdsMapper {
    AdsMapper INSTANSE = Mappers.getMapper(AdsMapper.class);
    @Mapping(source = "user.id", target = "author")
    @Mapping(source = "id", target = "pk")
    @Mapping(target = "image", expression =  "java(ads.getImage() != null ? ads.getImage().getUrl() : \"\")")
    AdDto adsToAdsDto(Ads ads);
    AdsDto listAdsToAdsDto(Integer count, List<Ads> results);
    Ads adsDtoToAds(CreateOrUpdateAdDto createOrUpdateAdDto);
//    @Mapping(source = "author", target = "user.id")
//    @Mapping(source = "pk", target = "id")
//    Ads adsDtoToAds(AdDto adDto);
//    @Mapping(source = "author.firstName", target = "authorFirstName")
//    @Mapping(source = "author.lastName", target = "authorLastName")
//    @Mapping(source = "author.username", target = "email")
//    @Mapping(source = "author.phone", target = "phone")
//    @Mapping(source = "id", target = "pk")
//    @Mapping(source = "images", target = "image")
//    FullAdsDto adsToFullAdsDto(Ads ads);


    }

