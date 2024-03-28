package ru.skypro.homework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.skypro.homework.dto.Role.USER;

@WebMvcTest(UserController.class)
@ContextConfiguration
@WithMockUser(username="user", authorities = "USER")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private Authentication authentication;
    @MockBean
    private UserServiceImpl userService;

    @Test
    public void getAuthUserInfoTest() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(testUser().getId());
        userDto.setFirstName(testUser().getFirstName());
        userDto.setLastName(testUser().getLastName());
        userDto.setEmail(testUser().getEmail());
        userDto.setPhone(testUser().getPhone());
        userDto.setRole(testUser().getRole());
        userDto.setImage(testUser().getAvatar().getUrl());

        when(userService.getAuthUserInfo(any(Authentication.class))).thenReturn(testUser());
        when(userMapper.userToUserDto(testUser())).thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/users/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userDto.getId()))
                .andExpect(jsonPath("$.email").value(userDto.getEmail()))
                .andExpect(jsonPath("$.firstName").value(userDto.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(userDto.getLastName()))
                .andExpect(jsonPath("$.phone").value(userDto.getPhone()))
                .andExpect(jsonPath("$.role").value(userDto.getRole().getRoleName()))
                .andExpect(jsonPath("$.image").value(userDto.getImage()));

    }
//
//    @Test
//    public void updateAuthUserInfoTest() throws Exception {
//        UpdateUserDto updateUserDto = new UpdateUserDto();
//        updateUserDto.setFirstName(testUser().getFirstName());
//        updateUserDto.setLastName(testUser().getLastName());
//        updateUserDto.setPhone(testUser().getPhone());
//
//        when(userService.updateAuthUserInfo(any(UpdateUserDto.class), any(Authentication.class))).thenReturn(updateUserDto);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .patch("/users/me")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(updateUserDto)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.firstName").value(updateUserDto.getFirstName()))
//                .andExpect(jsonPath("$.lastName").value(updateUserDto.getLastName()))
//                .andExpect(jsonPath("$.phone").value(updateUserDto.getPhone()));
//    }


//    @Test
//    public void updateAvatarTest() throws Exception {
//        when(userService.updateAvatar(any(MultipartFile.class), any(Authentication.class))).thenReturn(true);
//
//        mockMvc.perform(MockMvcRequestBuilders.patch("/users/me/image"))
//                .andExpect(status().isOk());
//    }

    private User testUser() {

        User user = new User();

        user.setId(1);
        user.setEmail("user");
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
        avatar.setFilePath("image\\54-min.jpg");
        avatar.setFileExtension("jpg");
        avatar.setFileSize(100);
        avatar.setMediaType("mediaType");

        return avatar;
    }
}
