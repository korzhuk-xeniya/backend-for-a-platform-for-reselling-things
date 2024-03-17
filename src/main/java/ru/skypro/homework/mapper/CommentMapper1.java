package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.entity.Comment;



@Mapper(componentModel = "spring")
public abstract class CommentMapper1 {
    @Mapping(target = "pk", source = "comment.id")
    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "text", source = "comment.text")
    @Mapping(target = "createdAt", source = "comment.createdAt", dateFormat = "yyyy-MM-dd HH:mm")
    public abstract CommentDto commentToDto(Comment comment);
}
