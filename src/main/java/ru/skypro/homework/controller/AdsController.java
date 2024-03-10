package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

//private final AdService adService;
    @GetMapping
    public ResponseEntity<?> getAllAds() { //List <Ad> вместо ?
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createAd( //
//                                       @RequestBody CreateOrUpdateAd adDTO,
                                       @RequestParam MultipartFile image) {
         return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInfoAd(@PathVariable int id) {//<ExtendedAd> вместо ?
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAd(@PathVariable int id) {//<AdDto> вместо ?
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateInfoAd(@PathVariable int id) //{<CreateOrUpdateAd> вместо ?
    {
         return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getAllUserAds() {// вместо ?  <List<AdsDto>>
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/{id}/image")
    public ResponseEntity<?> updateImageAd( // {вместо ?  <AdDto>
                                            @PathVariable int id,
                                            @RequestBody MultipartFile image)
    {
        return ResponseEntity.ok().build();
    }


}




