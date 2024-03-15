package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * User to UserDto.
     *
     * @param user
     * @return UserDto
     */
    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "role", source = "user.role")
//    @Mapping(target = "image", source = "user.avatar")
    UserDto userToUserDto(User user);

    /**
     * UserDro to User.
     *
     * @param userDto
     * @return User
     */
    @Mapping(target = "id", source = "userDto.id")
    @Mapping(target = "email", source = "userDto.email")
    @Mapping(target = "firstName", source = "userDto.firstName")
    @Mapping(target = "lastName", source = "userDto.lastName")
    @Mapping(target = "phone", source = "userDto.phone")
    @Mapping(target = "role", source = "userDto.role")
//    @Mapping(target = "image", source = "userDto")
    User userDtoToUser(UserDto userDto);

    /**
     * User to UpdateUserDto.
     *
     * @param user
     * @return UpdateUserDto
     */
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "phone", source = "user.phone")
    UpdateUserDto userToUpdateUserDto(User user);

    /**
     * UserDro to User.
     *
     * @param updateUserDto
     * @return User
     */
    @Mapping(target = "firstName", source = "updateUserDto.firstName")
    @Mapping(target = "lastName", source = "updateUserDto.lastName")
    @Mapping(target = "phone", source = "updateUserDto.phone")
    User updateUserDtoToUser(UpdateUserDto updateUserDto);

}
