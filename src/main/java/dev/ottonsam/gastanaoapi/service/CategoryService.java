package dev.ottonsam.gastanaoapi.service;

import dev.ottonsam.gastanaoapi.domain.dto.CategoryRequestDTO;
import dev.ottonsam.gastanaoapi.domain.dto.CategoryResponseDTO;
import dev.ottonsam.gastanaoapi.domain.model.Category;

import java.util.UUID;

public interface CategoryService {
    CategoryResponseDTO findById(Long id);
    CategoryResponseDTO create(CategoryRequestDTO categoryDTO);
}
