package com.unidthon.jabuhae.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponseDto {

  @Builder
  @Getter
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ResultDto {
    private Long UserId;
    private String email;
    private String name;
    private int HungerRange;
    private Long exp;
  }


}
