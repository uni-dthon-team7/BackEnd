package com.unidthon.jabuhae.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CookingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long cookingItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cooking_id", nullable = false, updatable = false)
    private Cooking cooking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false, updatable = false)
    private Item item;

    @Builder
    public CookingItem(Cooking cooking, Item item) {
        this.cooking = cooking;
        this.item = item;
    }
}