package dev.ottonsam.gastanaoapi.service.impl;

import dev.ottonsam.gastanaoapi.domain.dto.CategoryRequestDTO;
import dev.ottonsam.gastanaoapi.domain.dto.CategoryResponseDTO;
import dev.ottonsam.gastanaoapi.domain.model.Category;
import dev.ottonsam.gastanaoapi.domain.repository.CategoryRepository;
import dev.ottonsam.gastanaoapi.domain.repository.UserRepository;
import dev.ottonsam.gastanaoapi.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CategoryResponseDTO findById(Long id) {
        var category = categoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return new CategoryResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO create(CategoryRequestDTO categoryDTO) {
        var user = userRepository.findById(categoryDTO.userId()).orElseThrow(NoSuchElementException::new);
        var category = new Category(categoryDTO);
        category.setUser(user);
        var categoryResponse = categoryRepository.save(category);
        return new CategoryResponseDTO(categoryResponse);
    }
}
