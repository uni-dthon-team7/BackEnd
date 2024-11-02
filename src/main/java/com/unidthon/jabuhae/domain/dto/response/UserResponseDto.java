package com.unidthon.jabuhae.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
    private Long level;
    private Long nextExp;
    private String email;
    private String name;
    private int HungerRange;
    private Long exp;
  }


}
