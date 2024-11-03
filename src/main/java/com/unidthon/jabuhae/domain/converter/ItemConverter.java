package com.unidthon.jabuhae.domain.converter;

import com.unidthon.jabuhae.domain.dto.response.ItemResponseDto;
import com.unidthon.jabuhae.domain.dto.response.UserResponseDto;
import com.unidthon.jabuhae.domain.entity.Item;
import com.unidthon.jabuhae.domain.entity.User;
import java.util.List;
import java.util.stream.Collectors;

public class ItemConverter {
  public static ItemResponseDto.ResultDto toResultDTO(Item item) {
    return ItemResponseDto.ResultDto.builder()
        .itemId(item.getItemId())
        .imgPath(item.getImgPath())
        .name(item.getName())
        .build();
  }

  public static List<ItemResponseDto.ResultDto> toResultDTO(List<Item> item) {
    return item.stream()
        .map(ItemConverter::toResultDTO)
        .collect(Collectors.toList());
  }
}
