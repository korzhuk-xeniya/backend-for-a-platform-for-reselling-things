package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private Authentication authentication;
    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void getAuthUserInfoTest() {

        when(authentication.getName()).thenReturn(testUser().getEmail());
        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.ofNullable(testUser()));

        User result = userService.getAuthUserInfo(authentication);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(testUser().getId());
        assertThat(result.getEmail()).isEqualTo(testUser().getEmail());
        assertThat(result.getFirstName()).isEqualTo(testUser().getFirstName());
        assertThat(result.getLastName()).isEqualTo(testUser().getLastName());
        assertThat(result.getPassword()).isEqualTo(testUser().getPassword());
        assertThat(result.getPhone()).isEqualTo(testUser().getPhone());
        assertThat(result.getRole()).isEqualTo(testUser().getRole());
        assertThat(result.getAvatar().getId()).isEqualTo(testUser().getAvatar().getId());
        assertThat(result.getAvatar().getFilePath()).isEqualTo(testUser().getAvatar().getFilePath());
        assertThat(result.getAvatar().getFileExtension()).isEqualTo(testUser().getAvatar().getFileExtension());
        assertThat(result.getAvatar().getFileSize()).isEqualTo(testUser().getAvatar().getFileSize());
        assertThat(result.getAvatar().getMediaType()).isEqualTo(testUser().getAvatar().getMediaType());

    }

    private User testUser() {

        User user = new User();

        user.setId(1);
        user.setEmail("email");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPassword("password");
        user.setPhone("+79991112233");
        user.setRole(Role.USER);
        user.setAvatar(avatar());

        return user;
    }

    private Image avatar() {

        Image avatar = new Image();

        avatar.setId(10);
        avatar.setFilePath("/image/1");
        avatar.setFileExtension("fileExtension");
        avatar.setFileSize(100);
        avatar.setMediaType("mediaType");

        return avatar;
    }

    private Authentication auth() {

        Authentication auth = new UsernamePasswordAuthenticationToken(testUser(), null);

        return auth;
    }
}
