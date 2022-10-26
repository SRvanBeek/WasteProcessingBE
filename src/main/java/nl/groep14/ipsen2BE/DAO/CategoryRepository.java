package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
