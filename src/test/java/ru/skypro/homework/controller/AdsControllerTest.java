package ru.skypro.homework.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@RunWith(SpringRunner.class)
@WebMvcTest(AdsController.class)
 class AdsControllerTests {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private AdsService adsService;

        @MockBean
        private ImageService imageService;

        @MockBean
        private AdsMapper adsMapper;

        @Test
        public void testGetAllAds() throws Exception {
            List<Ads> ads = new ArrayList<>();
            // Заполните ads тестовыми данными

            when(adsService.getAllAds()).thenReturn(ads);
            when(adsMapper.listAdsToAdsDto(anyInt(), anyList())).thenReturn(new AdsDto());

            mockMvc.perform(get("/ads"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }

//        @Test
//        public void testAddAd() throws Exception {
//            CreateOrUpdateAdDto adDto = new CreateOrUpdateAdDto();
//            // Заполните adDto тестовыми данными
//
//            Ads ads = new Ads();
//            // Заполните ad тестовыми данными
//
//            when(adsService.saveAd(any(CreateOrUpdateAdDto.class), anyString(), any(MultipartFile.class))).thenReturn(ads);
//            when(adsMapper.adsToAdsDto(any(Ads.class))).thenReturn(new AdDto());
//
//            mockMvc.perform(multipart("/ads")
//                            .file("properties", objectMapper.writeValueAsString(adDto))
//                            .file("image", "testImage".getBytes()))
//                    .andExpect(status().isOk())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//        }
//
//        // Добавьте остальные тесты для других методов контроллера


    @Test
    void getInfoAd() {
    }

    @Test
    void removeAd() {
    }

    @Test
    void updateInfoAd() {
    }

    @Test
    void getAllUserAds() {
    }

    @Test
    void updateImageAd() {
    }
}