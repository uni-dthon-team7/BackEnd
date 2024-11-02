package com.unidthon.jabuhae.domain.dto.request;

import com.unidthon.jabuhae.domain.entity.Recipe;
import com.unidthon.jabuhae.domain.entity.User;
import jakarta.annotation.Nullable;

public record RecipeCreateRequestDto (
        Long userId,
        @Nullable Long parentRecipeId,
        String name,
        int avgCookingTime,
        String imgPath
){
    public Recipe toEntity(User user) {
        return Recipe.builder()
                .name(this.name)
                .imgPath(this.imgPath)
                .avgCookingTime(this.avgCookingTime)
                .parentRecipe(null)
                .creator(user)
                .build();
    }

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
