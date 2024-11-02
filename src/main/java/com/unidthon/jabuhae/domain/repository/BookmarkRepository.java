package com.unidthon.jabuhae.domain.repository;

import com.unidthon.jabuhae.domain.entity.Bookmark;
import com.unidthon.jabuhae.domain.entity.Recipe;
import com.unidthon.jabuhae.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findAllByRecipeAndUser(Recipe recipe, User user);
}