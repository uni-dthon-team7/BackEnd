package com.unidthon.jabuhae.domain.service;

import com.unidthon.jabuhae.domain.converter.CommentConverter;
import com.unidthon.jabuhae.domain.dto.request.CommentRequestDto;
import com.unidthon.jabuhae.domain.entity.Comment;
import com.unidthon.jabuhae.domain.entity.Recipe;
import com.unidthon.jabuhae.domain.entity.User;
import com.unidthon.jabuhae.domain.repository.CommentRepository;
import com.unidthon.jabuhae.domain.repository.RecipeRepository;
import com.unidthon.jabuhae.domain.repository.UserRepository;
import com.unidthon.jabuhae.global.exception.CustomException;
import com.unidthon.jabuhae.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
  private final CommentRepository commentRepository;
  private final RecipeRepository recipeRepository;
  private final UserRepository userRepository;

  public List<Comment> getComments(Long recipeId) {

    Recipe recipe = recipeRepository.findById(recipeId)
        .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND));

    return commentRepository.findAllByRecipe(recipe);
  }

  @Transactional
  public Comment createComment(CommentRequestDto.createDto requestDto) {

    Recipe recipe = recipeRepository.findById(requestDto.getRecipeId())
        .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND));

    User user = userRepository.findById(requestDto.getUserId())
        .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND));


    Comment comment = CommentConverter.toComment(requestDto, user, recipe);

    return commentRepository.save(comment);

  }

}
