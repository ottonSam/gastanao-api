package br.com.ottonsam.gastanaoapi.service;

import br.com.ottonsam.gastanaoapi.entity.Category;
import br.com.ottonsam.gastanaoapi.entity.User;
import br.com.ottonsam.gastanaoapi.entity.dtos.CreateCategoryDto;
import br.com.ottonsam.gastanaoapi.entity.dtos.ResponseCategoryDto;
import br.com.ottonsam.gastanaoapi.repository.CategoryRepository;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    private Optional<User> getUserData(JwtAuthenticationToken token) {
        Optional<User> user = Optional.ofNullable(userService.findByUsername(token.getName()));
        Assert.notNull(user, "User not found");
        return user;
    }

    public ResponseCategoryDto createCategory(CreateCategoryDto categoryDto, JwtAuthenticationToken token) {
        Optional<User> user = getUserData(token);
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
                Assert.isTrue(categoryToUpdate.getUser().equals(user.get()), "This category does not belong to the user");
                if (categoryToUpdate.getUser().equals(user.get())) {
                    categoryToUpdate.setDescription(categoryDto.description());
                    categoryRepository.save(categoryToUpdate);

                    return new ResponseCategoryDto(categoryToUpdate);
                }
            }
        }

        return null;
    }

    public Boolean deleteCategory(UUID id, JwtAuthenticationToken token) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            Optional<User> user = Optional.ofNullable(userService.findByUsername(token.getName()));
            if (user.isPresent()) {
                Assert.isTrue(category.get().getUser().equals(user.get()), "This category does not belong to the user");
                if (category.get().getUser().equals(user.get())) {
                    categoryRepository.delete(category.get());
                    return true;
                }
            }
        }
        return false;
    }
}
