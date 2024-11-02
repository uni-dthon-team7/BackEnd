package com.unidthon.jabuhae.domain.controller;

import com.unidthon.jabuhae.domain.converter.ItemConverter;
import com.unidthon.jabuhae.domain.converter.UserConverter;
import com.unidthon.jabuhae.domain.entity.Item;
import com.unidthon.jabuhae.domain.entity.User;
import com.unidthon.jabuhae.domain.model.ItemType;
import com.unidthon.jabuhae.domain.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Item")
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
  private final ItemService itemService;
  @Operation(summary = "물품 조회")
  @GetMapping("")
  public ResponseEntity<?> updateUser (@RequestParam ItemType itemType) {
    List<Item> item = itemService.getItemsByItemType(itemType);
    return ResponseEntity.ok(ItemConverter.toResultDTO(item));
  }
}
