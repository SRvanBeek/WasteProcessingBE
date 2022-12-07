package nl.groep14.ipsen2BE.DAO;
import nl.groep14.ipsen2BE.Models.Cutwaste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CutWasteRepository extends JpaRepository<Cutwaste, Long> {
    List<Cutwaste> findByType(String type);
}
