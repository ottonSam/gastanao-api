package br.com.ottonsam.gastanaoapi.service;

import br.com.ottonsam.gastanaoapi.entity.Category;
import br.com.ottonsam.gastanaoapi.entity.User;
import br.com.ottonsam.gastanaoapi.entity.dtos.CreateCategoryDto;
import br.com.ottonsam.gastanaoapi.entity.dtos.ResponseCategoryDto;
import br.com.ottonsam.gastanaoapi.repository.CategoryRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;
    UserService userService;

    public CategoryService(CategoryRepository categoryRepository, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
    }

    public Category getCategoryData(JwtAuthenticationToken token, UUID categoryId) {
        Optional<Category> category = findById(categoryId);
        if (category.isPresent()) {
            User user = userService.findByUsername(token.getName());
            if (category.get().getUser().equals(user)) {
                return category.get();
            }
            throw new IllegalArgumentException("This category does not belong to the user");
        }
        throw new IllegalArgumentException("Category not found");
    }

    public ResponseCategoryDto createCategory(CreateCategoryDto categoryDto, JwtAuthenticationToken token) {
        User user = userService.findByUsername(token.getName());
        Category category = new Category(categoryDto, user);
        categoryRepository.save(category);
        return new ResponseCategoryDto(category);
    }

    public List<ResponseCategoryDto> listAllCategories(JwtAuthenticationToken token) {
        User user = userService.findByUsername(token.getName());
        List<Category> categories = categoryRepository.findByUser(user);
        return categories.stream().map(ResponseCategoryDto::new).toList();
    }

    public ResponseCategoryDto updateCategory(CreateCategoryDto categoryDto, UUID id, JwtAuthenticationToken token) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Category categoryToUpdate = category.get();
            User user = userService.findByUsername(token.getName());
            if (categoryToUpdate.getUser().equals(user)) {
                categoryToUpdate.setDescription(categoryDto.description());
                categoryRepository.save(categoryToUpdate);
                return new ResponseCategoryDto(categoryToUpdate);
            }
            throw new IllegalArgumentException("This category does not belong to the user");
        }
        return null;
    }

    public Boolean deleteCategory(UUID id, JwtAuthenticationToken token) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            User user = userService.findByUsername(token.getName());
            if (category.get().getUser().equals(user)) {
                categoryRepository.delete(category.get());
                return true;
            }
            throw new IllegalArgumentException("This category does not belong to the user");
        }
        return false;
    }
}
