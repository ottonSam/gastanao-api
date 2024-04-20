package dev.ottonsam.gastanaoapi.controller;

import dev.ottonsam.gastanaoapi.domain.dto.CategoryRequestDTO;
import dev.ottonsam.gastanaoapi.domain.dto.CategoryResponseDTO;
import dev.ottonsam.gastanaoapi.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id) {
        var category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody CategoryRequestDTO categoryDTO) {
        var categoryCreated = categoryService.create(categoryDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryCreated.id())
                .toUri();
        return ResponseEntity.created(location).body(categoryCreated);
    }
}
