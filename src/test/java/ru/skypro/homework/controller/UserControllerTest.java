//package ru.skypro.homework.controller;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.mock.mockito.SpyBean;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import ru.skypro.homework.dto.Role;
//import ru.skypro.homework.entity.Image;
//import ru.skypro.homework.entity.User;
//import ru.skypro.homework.mapper.UserMapper;
//import ru.skypro.homework.repository.UserRepository;
//import ru.skypro.homework.service.impl.UserServiceImpl;
//
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static ru.skypro.homework.dto.Role.USER;
//
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @MockBean
//    private PasswordEncoder passwordEncoder;
//    @MockBean
//    private UserMapper userMapper;
//    @MockBean
//    private UserRepository userRepository;
//    @SpyBean
//    private UserServiceImpl userService;
//    @InjectMocks
//    private UserController userController;
//
//    private Integer id = 1;
//    private String email = "test@test.ru";
//    private String firstName = "testFirstName";
//    private String lastName = "testLastName";
//    private String password = "password";
//    private String phone = "+79998887766";
//    private Role role = USER;
//    Integer avatarId = 1;
//    String filePath = "qqq";
//    String fileExtension = "www";
//    long fileSize = 10l;
//    String mediaType = "eee";
//
//
//    @Test
//    @WithMockUser
//    public void getAuthUserInfoTest() throws Exception {
//        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.of(testUser()));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/users/me"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.email").value(email))
//                .andExpect(jsonPath("$.firstName").value(firstName))
//                .andExpect(jsonPath("$.lastName").value(lastName))
//                .andExpect(jsonPath("$.phone").value(phone))
//                .andExpect(jsonPath("$.role").value(role))
//                .andExpect(jsonPath("$.image").value(filePath));
//
//    }
//
//    private Authentication authToken() {
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken("test@test.ru", "password");
//
//        return authentication;
//    }
//    private User testUser() {
//
//        User user = new User();
//        user.setId(id);
//        user.setEmail(email);
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setPassword(password);
//        user.setPhone(phone);
//        user.setRole(role);
//
//        Image avatar = new Image();
//        avatar.setId(avatarId);
//        avatar.setFilePath(filePath);
//        avatar.setFileExtension(fileExtension);
//        avatar.setFileSize(fileSize);
//        avatar.setMediaType(mediaType);
//
//        user.setAvatar(avatar);
//
//        return user;
//    }
//}
