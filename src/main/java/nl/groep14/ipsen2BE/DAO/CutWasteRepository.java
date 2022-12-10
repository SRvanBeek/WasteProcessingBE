package nl.groep14.ipsen2BE.DAO;
import nl.groep14.ipsen2BE.Models.Cutwaste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CutWasteRepository extends JpaRepository<Cutwaste, Long> {
    List<Cutwaste> findByType(String type);

    @Query(value = "SELECT c FROM Cutwaste c WHERE c.artikelnummer = :artikelnummer")
    Optional<Cutwaste> getCutwasteByArtikelnummer(@Param("artikelnummer") String artikelnummer);
}
