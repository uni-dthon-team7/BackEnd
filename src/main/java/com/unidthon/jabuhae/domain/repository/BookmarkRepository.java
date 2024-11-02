package com.unidthon.jabuhae.domain.repository;

import com.unidthon.jabuhae.domain.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}