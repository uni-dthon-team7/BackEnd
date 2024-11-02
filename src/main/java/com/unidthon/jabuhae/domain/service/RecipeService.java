package com.unidthon.jabuhae.domain.service;

import com.unidthon.jabuhae.domain.dto.common.RecipeItemUnitDto;
import com.unidthon.jabuhae.domain.dto.request.RecipeCreateRequestDto;
import com.unidthon.jabuhae.domain.dto.response.RecipeInfoResponseDto;
import com.unidthon.jabuhae.domain.entity.Recipe;
import com.unidthon.jabuhae.domain.entity.RecipeItem;
import com.unidthon.jabuhae.domain.entity.User;
import com.unidthon.jabuhae.domain.model.ItemType;
import com.unidthon.jabuhae.domain.repository.RecipeItemRepository;
import com.unidthon.jabuhae.domain.repository.RecipeRepository;
import com.unidthon.jabuhae.global.exception.CustomException;
import com.unidthon.jabuhae.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeItemRepository recipeItemRepository;
    private final UserService userService;

    // 레시피 추가
    public Long addRecipe(RecipeCreateRequestDto requestDto) {
        User creator = userService.getUserById(requestDto.userId());
        Recipe parentRecipe = findRecipeById(requestDto.parentRecipeId());
        Recipe newRecipe = requestDto.toEntity(creator, parentRecipe);
        Recipe savedRecipe = recipeRepository.save(newRecipe);
        return savedRecipe.getRecipeId();
    }

    // 단일 레시피 조회
    @Transactional(readOnly = true)
    public RecipeInfoResponseDto getRecipeInfo(Long recipeId) {
        Recipe recipe = findRecipeById(recipeId);
        List<RecipeItem> ingredients = recipeItemRepository.findAllByRecipeAndItem_ItemType(recipe, ItemType.INGREDIENT);
        List<RecipeItem> cookers = recipeItemRepository.findAllByRecipeAndItem_ItemType(recipe, ItemType.COOKER);
        return RecipeInfoResponseDto.from(recipe, ingredients, cookers);
    }


    @Transactional(readOnly = true)
    public Recipe findRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(()->new CustomException(ErrorCode.RESOURCE_NOT_FOUND));
    }
}
