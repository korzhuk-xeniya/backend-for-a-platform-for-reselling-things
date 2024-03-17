package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper
{
    CommentMapper INSTANSE = Mappers.getMapper(CommentMapper.class);
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "user.id", target = "author")
    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "localDateTimeToLong")
    @Mapping(source = "user.image.url", target = "authorImage")
    CommentDto toDTO(Comment comment);
    Comments toCommentsDTO(int count, List<Comment> resultComments);
    CreateOrUpdateComment toDTO3(Comment comment);
}
