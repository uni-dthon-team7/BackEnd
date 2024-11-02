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
        List<RecipeItemUnitDto> nonPrimaryIngredients,
        List<RecipeItemUnitDto> cookers

) {
    public static RecipeInfoResponseDto from(Recipe recipe,
                                             List<RecipeItem> ingredients,
                                             List<RecipeItem> cookers) {
        List<RecipeItemUnitDto> primaryIngredientDtos = filterAndMapToDto(ingredients, true);
        List<RecipeItemUnitDto> nonPrimaryIngredientDtos = filterAndMapToDto(ingredients, false);


        List<RecipeItemUnitDto> cookerDtos = cookers.stream()
                .map(RecipeItemUnitDto::from)
                .toList();

        return new RecipeInfoResponseDto(
                recipe.getName(),
                recipe.getCreator().getName(),
                recipe.getImgPath(),
                recipe.getAvgCookingTime(),
                recipe.getFrequency(),
                primaryIngredientDtos,
                nonPrimaryIngredientDtos,
                cookerDtos
        );
    }

    private static List<RecipeItemUnitDto> filterAndMapToDto(List<RecipeItem> recipeItems, boolean isPrimary) {
        return recipeItems.stream()
                .filter(item -> item.isPrimary() == isPrimary)
                .map(RecipeItemUnitDto::from)
                .toList();
    }
}
