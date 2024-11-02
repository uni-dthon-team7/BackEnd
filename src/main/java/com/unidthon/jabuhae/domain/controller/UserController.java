package com.unidthon.jabuhae.domain.controller;

import com.unidthon.jabuhae.domain.converter.UserConverter;
import com.unidthon.jabuhae.domain.dto.request.UserRequestDto;
import com.unidthon.jabuhae.domain.dto.response.UserResponseDto;
import com.unidthon.jabuhae.domain.service.UserService;
import com.unidthon.jabuhae.domain.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @Operation(summary = "유저 조회")
  @GetMapping("/{userId}")
  public ResponseEntity<?> getUser( @PathVariable Long userId) {
    User user = userService.getUserById(userId);
    Long level = userService.expToLevel(user.getExp());
    return ResponseEntity.ok(UserConverter.toResultDTO(user, level));
  }

  @Operation(summary = "식사하기")
  @PatchMapping("/{userId}/hunger")
  public ResponseEntity<?> updateUser (@PathVariable Long userId) {
    User user = userService.eatFood(userId);
    return ResponseEntity.ok(UserConverter.toResultDTO(user));
  }

  @Operation(summary = "회원가입")
  @PostMapping("/signUp")
  public ResponseEntity<?> signUpUser (@RequestBody UserRequestDto.signUp requestDto) {
    User user = UserConverter.toUser(requestDto);  // DTO -> Member
    user = userService.signUp(user);

    return ResponseEntity.ok(UserConverter.toResultDTO(user));
  }
}