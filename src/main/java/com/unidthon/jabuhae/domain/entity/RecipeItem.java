package com.unidthon.jabuhae.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecipeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeItemId;

    @ColumnDefault("true")
    private boolean isPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", updatable = false)
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", updatable = false)
    private Item item;

    @Builder
    public RecipeItem(Recipe recipe, Item item, boolean isPrimary) {
        this.recipe = recipe;
        this.item = item;
        this.isPrimary = isPrimary;
    }
}