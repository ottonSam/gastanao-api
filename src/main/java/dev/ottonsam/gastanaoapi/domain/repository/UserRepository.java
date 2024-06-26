package dev.ottonsam.gastanaoapi.domain.repository;

import dev.ottonsam.gastanaoapi.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByLogin(String login);
    UserDetails findByLogin(String login);
}
