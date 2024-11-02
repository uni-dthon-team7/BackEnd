package com.unidthon.jabuhae.domain.repository;

import com.unidthon.jabuhae.domain.entity.RecipeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeItemRepository extends JpaRepository<RecipeItem, Long> {
}