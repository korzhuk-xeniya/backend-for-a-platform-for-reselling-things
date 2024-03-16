package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private AdsRepository adsRepository;
    private UserRepository userRepository;
    @Override
    public Comment addCommentToAds(int id, CreateOrUpdateComment text) {
        User user = userRepository.findUserByEmail(SecurityContextHolder.getContext()
                .getAuthentication()
                .getName()).orElseThrow();
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setAds(adsRepository.findById(id).orElseThrow());
        comment.setText(text.getText());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(int adId, int commentId) {

    }

    @Override
    public CommentDto update(int adId, int commentId, CreateOrUpdateComment text) {
        User user = userRepository.findUserByEmail(SecurityContextHolder.getContext()
                .getAuthentication()
                .getName()).orElseThrow();
//        User user =; TODO
//                Comment comment = new Comment(user, LocalDateTime.now(),adsRepository.findById(adId),text);
//        return commentRepository.save(comment);
        return null;
    }

    @Override
    public Comments getComments(int id) {
        return null;
    }
}
