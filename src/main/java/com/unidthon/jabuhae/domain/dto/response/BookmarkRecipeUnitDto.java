package com.unidthon.jabuhae.domain.dto.response;

import com.unidthon.jabuhae.domain.entity.Recipe;

public record BookmarkRecipeUnitDto(
        Long recipeId,
        String name,
        String imgPath
){

    public static BookmarkRecipeUnitDto from(Recipe recipe){
        return new BookmarkRecipeUnitDto(
                recipe.getRecipeId(),
                recipe.getName(),
                recipe.getImgPath()
        );

    }
}
