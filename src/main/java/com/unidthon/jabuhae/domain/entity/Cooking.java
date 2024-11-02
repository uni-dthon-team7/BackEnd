package com.unidthon.jabuhae.domain.entity;

import com.unidthon.jabuhae.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cooking extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long cookingId;

    private String memo;

    private int cookingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @Builder
    public Cooking(String memo, int cookingTime, User user, Recipe recipe) {
        this.memo = memo;
        this.cookingTime = cookingTime;
        this.user = user;
        this.recipe = recipe;
    }
}