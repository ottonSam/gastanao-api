package br.com.ottonsam.gastanaoapi.entity.dtos;

import br.com.ottonsam.gastanaoapi.entity.Category;

import java.util.UUID;

public record ResponseCategoryDto(UUID id, String description) {
    public ResponseCategoryDto(Category category) {
        this(category.getId(), category.getDescription());
    }
}
