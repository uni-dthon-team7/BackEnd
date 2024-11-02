package com.unidthon.jabuhae.domain.dto.request;

import com.unidthon.jabuhae.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDto {

  @Getter
  public static class UpdateDto {

  }

  @Getter
  public static class GetDto {

  }

  @Getter
  public static class signUp {
    private String email;
    private String password;
    private String name;
  }


  @Getter
  public static class signIn {

  }

}
