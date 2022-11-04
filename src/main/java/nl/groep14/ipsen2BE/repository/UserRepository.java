package nl.groep14.ipsen2BE.repository;

import nl.groep14.ipsen2BE.Models.Order;
import nl.groep14.ipsen2BE.Models.Role;
import nl.groep14.ipsen2BE.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

