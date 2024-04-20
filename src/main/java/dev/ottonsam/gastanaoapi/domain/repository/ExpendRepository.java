package dev.ottonsam.gastanaoapi.domain.repository;

import dev.ottonsam.gastanaoapi.domain.model.Expend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpendRepository extends JpaRepository<Expend, Long> {
}
