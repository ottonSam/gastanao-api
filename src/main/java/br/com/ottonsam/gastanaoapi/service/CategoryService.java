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

    public ResponseCategoryDto createCategory(CreateCategoryDto categoryDto, JwtAuthenticationToken token) {
        Optional<User> user = Optional.ofNullable(userService.findByUsername(token.getName()));
        if (user.isPresent()) {
            Category category = new Category(categoryDto, user.get());
            categoryRepository.save(category);

            return new ResponseCategoryDto(category);
        }
        return null;
    }

    public List<ResponseCategoryDto> listAllCategories(JwtAuthenticationToken token) {
        Optional<User> user = Optional.ofNullable(userService.findByUsername(token.getName()));
        if (user.isPresent()) {
            List<Category> categories = categoryRepository.findByUser(user.get());

            return categories.stream().map(ResponseCategoryDto::new).toList();
        }

        return null;
    }

    public ResponseCategoryDto updateCategory(CreateCategoryDto categoryDto, UUID id, JwtAuthenticationToken token) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Category categoryToUpdate = category.get();

            Optional<User> user = Optional.ofNullable(userService.findByUsername(token.getName()));
            if (user.isPresent()) {
                if (categoryToUpdate.getUser().equals(user.get())) {
                    categoryToUpdate.setDescription(categoryToUpdate.getDescription());
                    categoryRepository.save(categoryToUpdate);

                    return new ResponseCategoryDto(categoryToUpdate);
                }
            }
        }

        return null;
    }
}
