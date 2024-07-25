package br.com.ottonsam.gastanaoapi.repository;

import br.com.ottonsam.gastanaoapi.entity.Category;
import br.com.ottonsam.gastanaoapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findByUser(User user);
    Optional<Category> findById(UUID id);
}
