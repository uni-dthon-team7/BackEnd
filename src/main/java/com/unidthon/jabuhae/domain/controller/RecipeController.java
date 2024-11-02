package com.unidthon.jabuhae.domain.controller;

import com.unidthon.jabuhae.domain.dto.request.RecipeCreateRequestDto;
import com.unidthon.jabuhae.domain.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Recipe", description = "Recipe 관련 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    @Operation(summary = "레시피 추가", description = "레시피를 추가합니다.")
    @PostMapping
    public ResponseEntity<?> addRecipe(@RequestBody @Valid RecipeCreateRequestDto requestDto){
        Long recipeId = recipeService.addRecipe(requestDto);
        return ResponseEntity.ok().body(recipeId);
    }

    @Operation(summary = "단일 레시피 정보 조회", description = "레시피를 조회합니다.")
    @GetMapping("/recipes/{recipeId}")
    public ResponseEntity<?> addRecipe(@PathVariable final Long recipeId){
        return ResponseEntity.ok().body(recipeService.getRecipeInfo(recipeId));
    }
}
