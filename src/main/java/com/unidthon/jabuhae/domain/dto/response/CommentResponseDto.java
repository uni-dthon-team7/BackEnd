package com.unidthon.jabuhae.domain.dto.response;

import com.unidthon.jabuhae.domain.entity.Recipe;
import com.unidthon.jabuhae.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommentResponseDto {
  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ResultDto {
    private Long commentId;
    private String content;
    private UserResponseDto.ResultDto user;
  }
}
