package com.unidthon.jabuhae.domain.converter;

import static java.lang.Math.pow;
import static java.lang.Math.round;

import com.unidthon.jabuhae.domain.dto.request.UserRequestDto;
import com.unidthon.jabuhae.domain.dto.response.UserResponseDto;
import com.unidthon.jabuhae.domain.entity.User;
import java.util.List;
import java.util.logging.Level;

public class UserConverter {
  public static UserResponseDto.ResultDto toResultDTO(User user, Long level) {

    return UserResponseDto.ResultDto.builder()
        .UserId(user.getUserId())
        .email(user.getEmail())
        .name(user.getName())
        .level(level)
        .nextExp( round(pow(1.1, level))*level*100 )
        .HungerRange(user.getHungerRange())
        .exp(user.getExp())
        .build();
  }

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
