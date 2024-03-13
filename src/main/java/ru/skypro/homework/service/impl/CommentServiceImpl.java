package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Override
    public Comment addCommentToAds(int id, CreateOrUpdateComment text) {
//        User user = ;TODO
//        Comment comment = new Comment();
//        comment.setUser(user);
//        comment.setCreatedAt(LocalDateTime.now());
//        comment.setAds(adsRepository.findById(id).orElseThrow());
//        comment.setText(text.getText());
//        return commentRepository.save(comment);
        return null;
    }

    @Override
    public void deleteComment(int adId, int commentId) {

    }

    @Override
    public CommentDto update(int adId, int commentId, CreateOrUpdateComment text) {
//        User user =; TODO
//                Comment comment = new Comment(user, LocalDateTime.now(),adsRepository.findById(adId),text);
//        return commentRepository.save(comment);
        return null;
    }
}
