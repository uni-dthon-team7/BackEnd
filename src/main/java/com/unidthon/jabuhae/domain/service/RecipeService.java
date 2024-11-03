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
        Long parentRecipeId = requestDto.parentRecipeId();
        Recipe newRecipe;
        if (parentRecipeId != null) {
            Recipe parentRecipe = findRecipeById(parentRecipeId);
            newRecipe = requestDto.toEntity(creator, parentRecipe);
        } else {
            newRecipe = requestDto.toEntity(creator);
        }
        Recipe savedRecipe = recipeRepository.save(newRecipe);
        return savedRecipe.getRecipeId();
    }

    // 단일 레시피 조회
    @Transactional(readOnly = true)
    public RecipeInfoResponseDto getRecipeInfo(Long recipeId) {
        Recipe recipe = findRecipeById(recipeId);
        List<RecipeItem> recipeItems = recipeItemRepository.findAllByRecipe(recipe);
        List<RecipeItemUnitDto> primaryIngredientDtos = filterAndMapToDto(recipeItems, true);
        List<RecipeItemUnitDto> nonPrimaryIngredientDtos = filterAndMapToDto(recipeItems, false);
        return RecipeInfoResponseDto.from(recipe, primaryIngredientDtos, nonPrimaryIngredientDtos);
    }

    @Transactional(readOnly = true)
    public Recipe findRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(()->new CustomException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    // 레시피 검색
    @Transactional(readOnly = true)
    public List<RecipeInfoResponseDto> searchRecipe(String keyword) {
        List<Recipe> recipes = recipeRepository.searchRecipesByKeyword(keyword);
        return recipes.stream().map(recipe -> getRecipeInfo(recipe.getRecipeId())).toList();
    }

    private List<RecipeItemUnitDto> filterAndMapToDto(List<RecipeItem> recipeItems, boolean isPrimary) {
        return recipeItems.stream()
                .filter(item -> item.isPrimary() == isPrimary)
                .map(RecipeItemUnitDto::from)
                .toList();
    }
}
