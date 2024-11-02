package com.unidthon.jabuhae.domain.dto.request;

import com.unidthon.jabuhae.domain.entity.Recipe;

public record RecipeCreateRequestDto (
        String name,
        int avgCookingTime,
        String imgPath
){
    public Recipe toEntity() {
        return Recipe.builder()
                .name(this.name)
                .avgCookingTime(this.avgCookingTime)
                .imgPath(this.imgPath)
                .build();
    }
}
