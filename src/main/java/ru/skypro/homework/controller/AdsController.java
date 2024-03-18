package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.ImageService;


import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    private final ImageService imageService;
    private final AdsService adsService;

    @Operation(summary = "Получение всех объявлений")
    @ApiResponse(responseCode = "200",
            description = "OK",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdsDto.class)))

    @GetMapping()
    public ResponseEntity<AdsDto> getAllAds() {
        log.info("Запрос всех обьявлений");
        return ResponseEntity.ok(AdsMapper.listAdsToAdsDto(adsService.getAllAds().size(), adsService.getAllAds()));
        //        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Добавление объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Объявление добавлено",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CreateOrUpdateAdDto.class))),
            @ApiResponse(responseCode = "401", description = "Не авторизован")})

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> addAd(@Valid @RequestPart(name = "properties") CreateOrUpdateAdDto createOrUpdateAdDto,
                                       @RequestPart(name = "image", required = false) MultipartFile file,
                                       Authentication authentication) {
        return ResponseEntity.ok(AdsMapper.adsToAdsDto(adsService.saveAd(createOrUpdateAdDto, authentication.getName(), file)));
    }


    @Operation(summary = "Получение информации об объявлении")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ExtendedAdDto.class))),
            @ApiResponse(responseCode = "401", description = "Не авторизован"),
            @ApiResponse(responseCode = "404", description = "Объявление не найдено")

    })
    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getInfoAd(@PathVariable
                                                   @Parameter(description = "id объявления",
                                                           required = true) Integer id) {
        return ResponseEntity.ok(new ExtendedAdDto());
    }

    @Operation(summary = "Удаление объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public void removeAd(@PathVariable @Parameter(description = "id объявления", required = true) Integer id) {

    }

    @Operation(summary = "Обновление информации об объявлении")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })

    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateInfoAd(@PathVariable
                                              @Parameter(description = "id объявления",
                                                      required = true) Integer id) {
        return ResponseEntity.ok(new AdDto());
    }

    @Operation(summary = "Получение объявлений авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
    })
    @GetMapping("/me")
    public ResponseEntity<AdsDto> getAllUserAds() {
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Обновление картинки объявления")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                            array = @ArraySchema(
                                    schema = @Schema(implementation = ExtendedAdDto.class)))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })

    @PatchMapping("/{id}/image")
    public ResponseEntity<ExtendedAdDto> updateImageAd(
            @PathVariable @Parameter(description = "id объявления", required = true) Integer id,
            @RequestBody MultipartFile image) {
        return ResponseEntity.ok().build();
    }

}




