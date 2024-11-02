package com.unidthon.jabuhae.domain.repository;

import com.unidthon.jabuhae.domain.entity.Recipe;
import com.unidthon.jabuhae.domain.entity.RecipeItem;
import com.unidthon.jabuhae.domain.model.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeItemRepository extends JpaRepository<RecipeItem, Long> {
    List<RecipeItem> findAllByRecipe(Recipe recipe);
    List<RecipeItem> findAllByRecipeAndItem_ItemType(Recipe recipe, ItemType itemType);
}