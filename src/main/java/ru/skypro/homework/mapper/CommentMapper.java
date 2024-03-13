package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.entity.Comment;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANSE = Mappers.getMapper(CommentMapper.class);
    CommentDto toDTO(Comment comment);
    Comments toDTO2(Comment comment);
    CreateOrUpdateComment toDTO3(Comment comment);
}
