package com.unidthon.jabuhae.domain.dto.response;

import com.unidthon.jabuhae.domain.dto.common.RecipeItemUnitDto;
import com.unidthon.jabuhae.domain.entity.Recipe;
import com.unidthon.jabuhae.domain.entity.RecipeItem;

import java.util.List;
import java.util.stream.Collectors;

public record RecipeInfoResponseDto (
        String recipeName,
        String userName,
        String imgPath,
        int avgCookingTime,
        int frequency,
        List<RecipeItemUnitDto> primaryIngredients,
        List<RecipeItemUnitDto> nonPrimaryIngredients
) {
    public static RecipeInfoResponseDto from(Recipe recipe,
                                             List<RecipeItemUnitDto> primaryIngredientDtos,
                                             List<RecipeItemUnitDto> nonPrimaryIngredientDtos) {
        return new RecipeInfoResponseDto(
                recipe.getName(),
                recipe.getCreator().getName(),
                recipe.getImgPath(),
                recipe.getAvgCookingTime(),
                recipe.getFrequency(),
                primaryIngredientDtos,
                nonPrimaryIngredientDtos
        );
    }
}
