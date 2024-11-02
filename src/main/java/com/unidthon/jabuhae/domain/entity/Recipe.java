package com.unidthon.jabuhae.domain.entity;

import com.unidthon.jabuhae.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Recipe extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;

    @Column(nullable = false)
    private String name;

    private String imgPath;

    private int avgCookingTime;

    @ColumnDefault("0")
    private int frequency;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_recipe_id", updatable = false)
    private Recipe parentRecipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeItem> recipeItems;

    @Builder
    public Recipe(String name, String imgPath, Integer avgCookingTime, Recipe parentRecipe, User creator) {
        this.name = name;
        this.imgPath = imgPath;
        this.avgCookingTime = avgCookingTime;
        this.frequency = 0;
        this.recipeItems = new ArrayList<>();
        this.parentRecipe = (parentRecipe != null) ? parentRecipe : null;
        this.creator = creator;
    }
}