package com.unidthon.jabuhae.domain.dto;

import com.unidthon.jabuhae.domain.entity.Bookmark;
import com.unidthon.jabuhae.domain.entity.Recipe;
import com.unidthon.jabuhae.domain.entity.User;
import jakarta.validation.constraints.NotNull;

public record BookmarkRequestDto(
        @NotNull Long userId,
        @NotNull Long recipeId
){

    public Bookmark toEntity(User user, Recipe recipe){
        return Bookmark.builder()
                .user(user)
                .recipe(recipe)
                .build();

    }
}
