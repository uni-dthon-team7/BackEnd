package com.unidthon.jabuhae.domain.converter;

import com.unidthon.jabuhae.domain.dto.request.UserRequestDto;
import com.unidthon.jabuhae.domain.dto.response.UserResponseDto;
import com.unidthon.jabuhae.domain.entity.User;

public class UserConverter {
  public static UserResponseDto.ResultDto toResultDTO(User user) {
    return UserResponseDto.ResultDto.builder()
        .UserId(user.getUserId())
        .email(user.getEmail())
        .name(user.getName())
        .HungerRange(user.getHungerRange())
        .exp(user.getExp())
        .build();
  }

  public static User toUser(UserRequestDto.signUp requestDto) {
    return User.builder()
        .email(requestDto.getEmail())
        .name(requestDto.getName())
        .password(requestDto.getPassword())
        .build();
  }

}
