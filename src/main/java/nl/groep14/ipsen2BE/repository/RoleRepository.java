package nl.groep14.ipsen2BE.repository;

import nl.groep14.ipsen2BE.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
