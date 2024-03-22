package ru.skypro.homework.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void shouldMapUserToUserDto() {
        //Описан User
        User user = new User();
        user.setId(1);
        user.setEmail("email");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPassword("password");
        user.setPhone("+78881112233");
        user.setRole(Role.USER);

        Image avatar = new Image();
        avatar.setId(1);
        avatar.setFilePath("filePath");
        avatar.setFileExtension("fileExtension");
        avatar.setFileSize(10);
        avatar.setMediaType("mediaType");

        user.setAvatar(avatar);

        //Маппинг в UserDto
        UserDto userDto = userMapper.userToUserDto(user);

        //Проверка соответствия
        assertThat(userDto).isNotNull();
        assertThat(userDto.getId()).isEqualTo(user.getId());
        assertThat(userDto.getEmail()).isEqualTo(user.getEmail());
        assertThat(userDto.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(userDto.getLastName()).isEqualTo(user.getLastName());
        assertThat(userDto.getPhone()).isEqualTo(user.getPhone());
        assertThat(userDto.getRole()).isEqualTo(user.getRole());
        assertThat(userDto.getImage()).isEqualTo(user.getAvatar().getFilePath());
    }

}
