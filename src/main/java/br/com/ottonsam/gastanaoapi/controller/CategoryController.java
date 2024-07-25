package br.com.ottonsam.gastanaoapi.controller;

import br.com.ottonsam.gastanaoapi.entity.dtos.CreateCategoryDto;
import br.com.ottonsam.gastanaoapi.entity.dtos.ResponseCategoryDto;
import br.com.ottonsam.gastanaoapi.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    ResponseEntity<List<ResponseCategoryDto>> getAllCategories(JwtAuthenticationToken token) {
        Optional<List<ResponseCategoryDto>> categories = Optional.ofNullable(categoryService.listAllCategories(token));

        return categories.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<ResponseCategoryDto> createCategory(JwtAuthenticationToken token, @RequestBody CreateCategoryDto categoryDto) {
        Optional<ResponseCategoryDto> category = Optional.ofNullable(categoryService.createCategory(categoryDto, token));

        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseCategoryDto> updateCategory(@PathVariable("id") UUID id, JwtAuthenticationToken token, @RequestBody CreateCategoryDto categoryDto) {
        Optional<ResponseCategoryDto> category = Optional.ofNullable(categoryService.updateCategory(categoryDto, id, token));

        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable("id") UUID id, JwtAuthenticationToken token) {
        if (categoryService.deleteCategory(id, token)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
