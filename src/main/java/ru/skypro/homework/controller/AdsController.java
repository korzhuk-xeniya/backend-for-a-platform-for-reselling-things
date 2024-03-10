package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.impl.ImageServiceImpl;

import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

private final ImageService imageService;
    @GetMapping()
    public ResponseEntity <AdsDto> getAllAds()
    {
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreateOrUpdateAdDto> createAd(@RequestBody CreateOrUpdateAdDto createOrUpdateAdDto)
//                                                        @RequestParam MultipartFile image)
        {

        return ResponseEntity.ok(new CreateOrUpdateAdDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getInfoAd(@PathVariable Integer id) {
        return ResponseEntity.ok(new ExtendedAdDto());
    }

    @DeleteMapping("/{id}")
    public void removeAd(@PathVariable Integer id) {

    }


    @PatchMapping("/{id}")
    public ResponseEntity<CreateOrUpdateAdDto> updateInfoAd(@PathVariable Integer id)
    {
         return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAllUserAds() {
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/{id}/image")
    public ResponseEntity<ExtendedAdDto> updateImageAd(
                                            @PathVariable Integer id,
                                            @RequestBody MultipartFile image)
    {
        return ResponseEntity.ok().build();
    }


}




