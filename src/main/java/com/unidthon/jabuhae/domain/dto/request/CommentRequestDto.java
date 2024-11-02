package com.unidthon.jabuhae.domain.dto.request;

import lombok.Getter;

public class CommentRequestDto {
  @Getter
  public static class createDto{
    private Long recipeId;
    private Long userId;
    private String content;
  }

}
