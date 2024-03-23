package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static liquibase.repackaged.org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    @Mock
    private AdsRepository adsRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private UserService userService;
    @Spy
    private CommentMapper commentMapper;
    @InjectMocks
    private CommentServiceImpl commentService;
    private Ads testAds;
    private Comment testComment;
    private CreateOrUpdateComment text;
    private User testUser;

    private Authentication auth;
    @BeforeEach
    void init() {
        testUser = new User();
        testUser.setId(100);
        testUser.setEmail("test@test.com");
        auth = new UsernamePasswordAuthenticationToken(testUser, null);

        testAds = new Ads();
        testAds.setId(1);
        testAds.setPrice(1000);
        testAds.setTitle("Test ads");

        testComment = new Comment();
        testComment.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1, 1));
        testComment.setText("Test comment");
        testComment.setId(1);
        testComment.setUser( testUser);

         text = commentMapper.toDTO3(testComment);
    }

    @Test
    void addCommentToAds_() {
//        when(userRepository.save(any(User.class))).thenReturn(testUser);
//        when(adsRepository.findById(anyInt())).thenReturn(Optional.ofNullable(testAds));
//        when(commentRepository.save(any(Comment.class))).thenReturn(testComment);
//        when(userService.getAuthUserInfo()).thenReturn(null);
//
//        Comment result = commentService.addCommentToAds(testAds.getId(), text,auth);
//
//        assertThat(result).isNotNull();
//        assertThat(result.getText()).isEqualTo(testComment.getText());
//        assertThat(result.getCreatedAt()).isEqualTo(testComment.getCreatedAt());
//
    }

    @Test
    void deleteComment() {
    }

    @Test
    void update() {
    }

    @Test
    void getComments_() {
        List<Comment> result = commentService.getComments(testAds.getId());

        assertThat(result).isNotNull();
        assertThat(result.contains(testComment));
    }
}