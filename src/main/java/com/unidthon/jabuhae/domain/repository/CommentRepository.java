package com.unidthon.jabuhae.domain.repository;

import com.unidthon.jabuhae.domain.entity.Comment;
import com.unidthon.jabuhae.domain.entity.Recipe;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByRecipe(Recipe recipe);
}