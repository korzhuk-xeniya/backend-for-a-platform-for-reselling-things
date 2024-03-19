package ru.skypro.homework.mapper;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Image;


@Mapper
public interface AdsMapper {
    @Named("IMAGE")
    default String IMAGE(Image image) {
        return image.getFilePath();

    }

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "image", target = "image", qualifiedByName = "IMAGE")
    @Mapping(source = "user.id", target = "author")
    AdDto adToAdsDto(Ads ads);



    }


//    private Integer author;
//    private String image;
//    private Integer pk;
//    private Integer price;
//    private String title;
//
//    private Integer id;
//    private Integer price;
//    private String title;
//    private User user;
//    private Image image;
//
//    }

