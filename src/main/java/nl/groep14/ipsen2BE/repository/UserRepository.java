package nl.groep14.ipsen2BE.repository;

import nl.groep14.ipsen2BE.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

