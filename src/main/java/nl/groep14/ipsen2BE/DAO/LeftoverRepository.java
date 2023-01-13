package nl.groep14.ipsen2BE.DAO;
import nl.groep14.ipsen2BE.Models.Leftover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeftoverRepository extends JpaRepository<Leftover, Long> {
    List<Leftover> findByType(String type);
    Optional<Leftover> getLeftoversByArtikelnummer(String artikelnummer);
    ArrayList<Leftover> getAllByProcessed(boolean processed);
}
