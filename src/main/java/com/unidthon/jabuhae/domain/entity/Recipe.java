package com.unidthon.jabuhae.domain.entity;

import com.unidthon.jabuhae.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    private int avgCookingTime;

    private String cookingMethod;

    @ColumnDefault("0")
    private int frequency;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeItem> recipeItems;

    @Builder
    public Recipe(String name, Integer avgCookingTime, String cookingMethod) {
        this.name = name;
        this.avgCookingTime = avgCookingTime;
        this.cookingMethod = cookingMethod;
        this.frequency = 0;
        this.recipeItems = new ArrayList<>();
    }
}