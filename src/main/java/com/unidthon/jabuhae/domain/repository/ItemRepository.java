package com.unidthon.jabuhae.domain.repository;

import com.unidthon.jabuhae.domain.entity.Item;
import com.unidthon.jabuhae.domain.model.ItemType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

  List<Item> findAllByItemType(ItemType itemType);
}