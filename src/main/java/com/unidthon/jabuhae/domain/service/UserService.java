package com.unidthon.jabuhae.domain.service;

import com.unidthon.jabuhae.domain.entity.User;
import com.unidthon.jabuhae.domain.repository.UserRepository;
import com.unidthon.jabuhae.global.exception.CustomException;
import com.unidthon.jabuhae.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  public User getUserById(Long userId) {

    return userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND));
  }

  @Transactional
  public User updateUser(Long userId, Long exp, int hunger) {

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND));

    user.updateExpAndHunger(exp, hunger);

    return userRepository.save(user);
  }

  public User eatFood(Long userId) {

    final Long toExp = 100L;
    final int toHunger = 40;

    return updateUser(userId, toExp, toHunger);
  }

  @Transactional
  public User signUp(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new CustomException(ErrorCode.RESOURCE_ALREADY_EXISTS);
    }

    return userRepository.save(user);
  }

}
