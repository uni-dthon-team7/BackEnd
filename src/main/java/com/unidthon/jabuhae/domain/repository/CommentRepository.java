package com.unidthon.jabuhae.domain.repository;

import com.unidthon.jabuhae.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}