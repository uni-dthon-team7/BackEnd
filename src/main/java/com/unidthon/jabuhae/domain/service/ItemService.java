package com.unidthon.jabuhae.domain.service;

import com.unidthon.jabuhae.domain.entity.Item;
import com.unidthon.jabuhae.domain.entity.User;
import com.unidthon.jabuhae.domain.model.ItemType;
import com.unidthon.jabuhae.domain.repository.ItemRepository;
import com.unidthon.jabuhae.domain.repository.UserRepository;
import com.unidthon.jabuhae.global.exception.CustomException;
import com.unidthon.jabuhae.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
  private final ItemRepository itemRepository;
  public List<Item> getItemsByItemType(ItemType itemType) {

    return itemRepository.findAllByItemType(itemType);
  }
}
