package ru.skypro.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.mapper.AdsMapperImpl;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class HomeworkApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
    }

    @PostConstruct
    public void test() {
        Ads ads = new Ads(1, 2222, "IIII", null, Image.builder()
                .filePath("String")
                .fileSize(1024).build());

        AdsMapper adsMapper = new AdsMapperImpl();
        System.out.println(adsMapper.adToAdsDto(ads));
    }
}
