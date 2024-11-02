package com.unidthon.jabuhae.domain.service;

import com.unidthon.jabuhae.domain.dto.request.BookmarkRequestDto;
import com.unidthon.jabuhae.domain.dto.response.BookmarkRecipeUnitDto;
import com.unidthon.jabuhae.domain.entity.Bookmark;
import com.unidthon.jabuhae.domain.entity.Recipe;
import com.unidthon.jabuhae.domain.entity.User;
import com.unidthon.jabuhae.domain.repository.BookmarkRepository;
import com.unidthon.jabuhae.domain.repository.RecipeRepository;
import com.unidthon.jabuhae.domain.repository.UserRepository;
import com.unidthon.jabuhae.global.exception.CustomException;
import com.unidthon.jabuhae.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final BookmarkRepository bookmarkRepository;

    // 북마크 상태 변경
    public boolean changeBookmark(BookmarkRequestDto requestDto) {
        User user = userRepository.findById(requestDto.userId())
                .orElseThrow(()->new CustomException(ErrorCode.RESOURCE_NOT_FOUND));
        Recipe recipe = recipeRepository.findById(requestDto.recipeId())
                .orElseThrow(()->new CustomException(ErrorCode.RESOURCE_NOT_FOUND));

        List<Bookmark> bookmarks = bookmarkRepository.findAllByRecipeAndUser(recipe, user);
        if (bookmarks.isEmpty()) {
            Bookmark newBookmark = Bookmark.builder()
                    .user(user)
                    .recipe(recipe)
                    .build();
            bookmarkRepository.save(newBookmark);
            return true;
        } else  { // 북마크가 존재하면 삭제
            bookmarkRepository.deleteAll(bookmarks);
            return false;
        }
    }

    // 유저의 북마크 조회
    public List<BookmarkRecipeUnitDto> getUserBookmarks(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new CustomException(ErrorCode.RESOURCE_NOT_FOUND));
        List<Bookmark> bookmarks = bookmarkRepository.findAllByUser(user);
        List<BookmarkRecipeUnitDto> recipeDtos = bookmarks.stream()
                .map(bookmark -> {
                    Recipe recipe = bookmark.getRecipe(); // Assuming getRecipe() returns the Recipe object
                    return BookmarkRecipeUnitDto.from(recipe);
                })
                .collect(Collectors.toList());
        return recipeDtos;
    }
}
