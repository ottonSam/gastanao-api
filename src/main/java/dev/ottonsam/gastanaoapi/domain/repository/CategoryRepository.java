package dev.ottonsam.gastanaoapi.domain.repository;

import dev.ottonsam.gastanaoapi.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
