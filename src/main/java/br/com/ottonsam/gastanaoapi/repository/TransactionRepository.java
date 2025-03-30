package br.com.ottonsam.gastanaoapi.repository;

import br.com.ottonsam.gastanaoapi.entity.Category;
import br.com.ottonsam.gastanaoapi.entity.Transaction;
import br.com.ottonsam.gastanaoapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByUser(User user);
    List<Transaction> findByCategory(Category category);
    Optional<Transaction> findById(UUID id);
}
