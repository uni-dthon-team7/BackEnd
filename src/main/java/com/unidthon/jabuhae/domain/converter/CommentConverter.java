package com.unidthon.jabuhae.domain.converter;

import com.unidthon.jabuhae.domain.dto.request.CommentRequestDto;
import com.unidthon.jabuhae.domain.dto.request.UserRequestDto;
import com.unidthon.jabuhae.domain.dto.response.CommentResponseDto;
import com.unidthon.jabuhae.domain.dto.response.UserResponseDto;
import com.unidthon.jabuhae.domain.entity.Comment;
import com.unidthon.jabuhae.domain.entity.Recipe;
import com.unidthon.jabuhae.domain.entity.User;
import com.unidthon.jabuhae.domain.service.UserService;
import java.util.List;
import java.util.stream.Collectors;

public class CommentConverter {
  public static CommentResponseDto.ResultDto toResultDTO(Comment comment) {
    return CommentResponseDto.ResultDto.builder()
        .content(comment.getContent())
        .commentId(comment.getCommentId())
        .user(comment.getUser())
        .build();
  }

  public static Comment toComment(CommentRequestDto.createDto requestDto, User user, Recipe recipe) {

    return Comment.builder()
        .user(user)
        .content(requestDto.getContent())
        .recipe(recipe)
        .build();
  }
  public static List<CommentResponseDto.ResultDto> toResultDTO(List<Comment> issues) {
    return issues.stream()
        .map(CommentConverter::toResultDTO)
        .collect(Collectors.toList());
  }

}
