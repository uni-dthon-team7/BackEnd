package com.unidthon.jabuhae.domain.entity;

import com.unidthon.jabuhae.domain.model.ItemType;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long itemId;

    @Column(nullable = false)
    private ItemType itemType;

    @Column(nullable = false)
    private String name;

    private String imgPath;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeItem> recipeItems;

    @Builder
    public Item(ItemType itemType, String name, String imgPath) {
        this.itemType = itemType;
        this.name = name;
        this.imgPath = imgPath;
        this.recipeItems = new ArrayList<>();
    }
}