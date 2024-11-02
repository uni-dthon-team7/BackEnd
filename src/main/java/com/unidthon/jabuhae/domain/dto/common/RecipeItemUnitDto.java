package com.unidthon.jabuhae.domain.dto.common;

import com.unidthon.jabuhae.domain.entity.RecipeItem;

public record RecipeItemUnitDto(
        String name,
        String imgPath
) {
    // Static factory method to convert from RecipeItem
    public static RecipeItemUnitDto from(RecipeItem recipeItem) {
        return new RecipeItemUnitDto(
                recipeItem.getItem().getName(),
                recipeItem.getItem().getImgPath()
        ); // adjust fields as per RecipeItem structure
    }
}
