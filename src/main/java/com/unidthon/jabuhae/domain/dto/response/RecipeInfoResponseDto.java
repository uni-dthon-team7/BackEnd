package com.unidthon.jabuhae.domain.dto.response;

import com.unidthon.jabuhae.domain.dto.common.RecipeItemUnitDto;
import com.unidthon.jabuhae.domain.entity.Recipe;
import com.unidthon.jabuhae.domain.entity.RecipeItem;

import java.util.List;

public record RecipeInfoResponseDto (
        String recipeName,
        String userName,
        String imgPath,
        int avgCookingTime,
        int frequency,
        List<RecipeItemUnitDto> ingredients,
        List<RecipeItemUnitDto> cookers

) {
    public static RecipeInfoResponseDto from(Recipe recipe,
                                             List<RecipeItem> ingredients,
                                             List<RecipeItem> cookers) {
        List<RecipeItemUnitDto> ingredientDtos = ingredients.stream()
                .map(RecipeItemUnitDto::from)  // Using RecipeItemUnitDto's from method
                .toList();

        List<RecipeItemUnitDto> cookerDtos = cookers.stream()
                .map(RecipeItemUnitDto::from)
                .toList();

        return new RecipeInfoResponseDto(
                recipe.getName(),
                recipe.getCreator().getName(),
                recipe.getImgPath(),
                recipe.getAvgCookingTime(),
                recipe.getFrequency(),
                ingredientDtos,
                cookerDtos
        );
    }
}
