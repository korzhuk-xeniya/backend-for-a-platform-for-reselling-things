package ru.skypro.homework.controller;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.MockMvc;

import ru.skypro.homework.service.ImageService;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ImageControllerTest {

    @Mock
    private ImageService imageService;
    @InjectMocks
    ImageController imageController;
   // @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

//    @Test
//    public void testTransferImageToResponse_Exception() throws Exception {
//        //when(imageService.saveImageFile(any(MultipartFile.class))).thenReturn(status().isOk()));
//        // Устанавливаем, что при вызове метода saveImageFile возникает исключение IOException
//        when(imageService.saveImageFile(any(MultipartFile.class));
//
//        // Проверяем, что возвращается статус 200
//        mockMvc.perform(get("/image/1"))
//                .andExpect(status().isOk());
//        //Проверяем что ImageService
//        verify(imageService,times(1));
//    }


     @Test
    public void testTransferImageToResponse_Success() throws Exception {
        // Устанавливаем ожидаемый результат
        byte[] imageData = "test image data".getBytes();
        // when(imageService.transferImageToResponse(eq(1), any(HttpServletResponse.class))).thenReturn(imageData);

         // Выполняем запрос и проверяем успешный ответ
        mockMvc.perform(get("/image/1"));
//                .andExpect(status().isOk())
//                .andExpect(content().bytes(imageData));

         T result =imageService.transferImageToResponse(1, any(HttpServletResponse.class))
         verify(result, times(1));
     }

    @Test
    public void testTransferImageToResponse_NotFound() throws Exception {
        // Устанавливаем, что изображение с запрошенным идентификатором отсутствует
        when(imageService.transferImageToResponse(eq(1), any(HttpServletResponse.class))).thenReturn(null);

        // Проверяем, что возвращается статус 404 Not Found
        mockMvc.perform(get("/image/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testTransferImageToResponse_Exception() throws Exception {
        // Устанавливаем, что при вызове сервиса возникает исключение
        doThrow(IOException.class).when(imageService).transferImageToResponse(eq(1), any(HttpServletResponse.class));

        // Проверяем, что возвращается статус 500 Internal Server Error
        mockMvc.perform(get("/image/1"))
                .andExpect(status().isInternalServerError());
    }
}
