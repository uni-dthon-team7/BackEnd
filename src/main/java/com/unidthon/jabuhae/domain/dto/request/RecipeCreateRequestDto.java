package com.unidthon.jabuhae.domain.dto.request;

import com.unidthon.jabuhae.domain.entity.Recipe;
import com.unidthon.jabuhae.domain.entity.User;

public record RecipeCreateRequestDto (
        Long userId,
        Long parentRecipeId,
        String name,
        int avgCookingTime,
        String imgPath
){
    public Recipe toEntity(User user, Recipe parentRecipe) {
        return Recipe.builder()
                .name(this.name)
                .imgPath(this.imgPath)
                .avgCookingTime(this.avgCookingTime)
                .parentRecipe(parentRecipe)
                .creator(user)
                .build();
    }
}
