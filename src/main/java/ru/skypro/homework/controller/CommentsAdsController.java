package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.impl.CommentServiceImpl;

import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class CommentsAdsController {
    private final CommentService commentsService;
//    private final AdsService adsService;

    /**
     * @param id id объявления
     * @return все комментарии объявления по id объявления
     */
    @Operation(summary = "Получение комментариев объявления",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)
                            )),
                    @ApiResponse(responseCode = "400",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)

                            )),
                    @ApiResponse(responseCode = "404",
                            description = "Not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)

                            ))})

    @GetMapping("/ads/{id}/comments")
    public ResponseEntity<Comments> getComments(@Parameter(description = "ads id") @RequestParam int id) {
        List<Comment> comment = commentsService.getComments(id);
        return ResponseEntity.ok(CommentMapper.INSTANSE.toCommentsDTO(comment.size(), comment));
    }

    /**
     * @param id   id объявления
     * @param text текст комментария
     * @return Добавленный комментарий к объявлению
     */
    @Operation(summary = "Добавление комментария к объявлению",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)
                            )),
                    @ApiResponse(responseCode = "400",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)

                            )),
                    @ApiResponse(responseCode = "404",
                            description = "Not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)

                            ))})
    @PostMapping("/ads/{id}/comments")
    public CommentDto addComment(@RequestParam int id, @RequestBody CreateOrUpdateComment text,
                                 Authentication authentication) {
        Comment comment = commentsService.addCommentToAds(id, text,authentication); //TODO
        return CommentMapper.INSTANSE.toDTO(comment);

    }

    /**
     * @param adId      id объявления
     * @param commentId id комментария
     */
    @Operation(summary = "Удаление комментария",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)
                            )),
                    @ApiResponse(responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)

                            )),
                    @ApiResponse(responseCode = "403",
                            description = "Forbidden",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)

                            )),
                    @ApiResponse(responseCode = "404",
                            description = "Not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)

                            ))})
    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    public void deleteComment(@RequestParam int adId, @RequestParam int commentId, Authentication authentication) {
        commentsService.deleteComment(adId, commentId, authentication);

    }

    /**
     * @param adId      id объявления
     * @param commentId id комментария
     * @param text      новый текст комментария
     * @return обновленный комментарий
     */
    @Operation(summary = "Обновление комментария",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)
                            )),
                    @ApiResponse(responseCode = "401",
                            description = "Unauthorized",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)

                            )),
                    @ApiResponse(responseCode = "403",
                            description = "Forbidden",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)

                            )),
                    @ApiResponse(responseCode = "404",
                            description = "Not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)

                            ))})
    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@RequestParam int adId, @RequestParam int commentId,
                                    @RequestBody CreateOrUpdateComment text,
                                    Authentication authentication) {
        return ResponseEntity.ok(commentsService.update(adId, commentId, text,authentication));

    }
}
