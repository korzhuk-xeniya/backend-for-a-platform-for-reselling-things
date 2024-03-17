package ru.skypro.homework.mapper;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.entity.Ads;


@Mapper(componentModel = "spring")
public interface AdsMapper {
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "images", target = "image")
    AdsDto adsToAdsDto(Ads ads);

    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "pk", target = "id")
    Ads adsDtoToAds(AdsDto adsDto);
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.username", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "images", target = "image")
    FullAdsDto adsToFullAdsDto(Ads ads);


    }

