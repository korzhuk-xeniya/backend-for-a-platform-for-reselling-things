package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Comment;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import static liquibase.repackaged.net.sf.jsqlparser.parser.feature.Feature.comment;

@Mapper(componentModel = "spring")
public interface CommentMapper
{
    CommentMapper INSTANSE = Mappers.getMapper(CommentMapper.class);
    @Mapping(source = "id", target = "pk")
    @Mapping(source = "user.id", target = "author")
    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm")
//    @Mapping(source = "user.image.url", target = "authorImage")
    CommentDto toDTO(Comment comment);
    Comments toCommentsDTO(Integer count, List<Comment> resultComments);
    CreateOrUpdateComment toDTO3(Comment comment);


}
