package com.unidthon.jabuhae.domain.controller;

import com.unidthon.jabuhae.domain.converter.CommentConverter;
import com.unidthon.jabuhae.domain.converter.UserConverter;
import com.unidthon.jabuhae.domain.dto.request.CommentRequestDto;
import com.unidthon.jabuhae.domain.entity.Comment;
import com.unidthon.jabuhae.domain.entity.User;
import com.unidthon.jabuhae.domain.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Comments")
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
  private final CommentService commentService;
  @Operation(summary = "댓글 목록 조회")
  @GetMapping("/recipes/{recipeId}/comments")
  public ResponseEntity<?> getComments( @PathVariable Long recipeId) {
    List<Comment> comments = commentService.getComments(recipeId);
    return ResponseEntity.ok(CommentConverter.toResultDTO(comments));
  }


  @Operation(summary = "댓글 생성")
  @PostMapping("")
  public ResponseEntity<?> createComment( @RequestBody CommentRequestDto.createDto requestDto) {

    Comment comment = commentService.createComment(requestDto);
    return ResponseEntity.ok(CommentConverter.toResultDTO(comment));
  }
}
