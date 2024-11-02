package com.unidthon.jabuhae.domain.repository;

import com.unidthon.jabuhae.domain.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}