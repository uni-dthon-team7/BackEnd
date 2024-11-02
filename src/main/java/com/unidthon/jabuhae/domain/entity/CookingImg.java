package com.unidthon.jabuhae.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CookingImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cookingImgId;

    private String imgPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cooking_id", updatable = false)
    private Cooking cooking;

    @Builder
    public CookingImg(String imgPath, Cooking cooking) {
        this.imgPath = imgPath;
        this.cooking = cooking;
    }
}