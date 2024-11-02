package com.unidthon.jabuhae.domain.service;

import com.unidthon.jabuhae.domain.dto.request.RecipeCreateRequestDto;
import com.unidthon.jabuhae.domain.entity.Recipe;
import com.unidthon.jabuhae.domain.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

    // 레시피 추가
    public Long addRecipe(RecipeCreateRequestDto requestDto) {
        Recipe newRecipe = requestDto.toEntity();
        Recipe savedRecipe = recipeRepository.save(newRecipe);
        return savedRecipe.getRecipeId();
    }
}
