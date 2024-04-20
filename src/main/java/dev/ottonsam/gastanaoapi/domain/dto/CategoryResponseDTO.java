package dev.ottonsam.gastanaoapi.domain.dto;

import dev.ottonsam.gastanaoapi.domain.model.Category;

import java.util.List;

public record CategoryResponseDTO (Long id, String name){
    public CategoryResponseDTO (Category category) {
        this(category.getId(), category.getName());
    }
}
