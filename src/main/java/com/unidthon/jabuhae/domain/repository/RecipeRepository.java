package com.unidthon.jabuhae.domain.repository;

import com.unidthon.jabuhae.domain.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("SELECT r FROM Recipe r JOIN r.creator c " +
            "WHERE r.name LIKE %:keyword% OR c.name LIKE %:keyword% " +
            "OR EXISTS (SELECT ri FROM RecipeItem ri WHERE ri.recipe = r AND ri.item.name LIKE %:keyword%)")
    List<Recipe> searchRecipesByKeyword(@Param("keyword") String keyword);
}