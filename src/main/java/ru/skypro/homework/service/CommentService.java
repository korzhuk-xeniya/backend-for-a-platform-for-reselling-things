package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Comment;

public interface CommentService {
    Comment addCommentToAds(int id, CreateOrUpdateComment text);

    void deleteComment(int adId, int commentId);

    CommentDto update(int adId, int commentId, CreateOrUpdateComment text);

    Comments getComments(int id);
}
