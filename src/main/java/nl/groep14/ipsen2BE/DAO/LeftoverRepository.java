package nl.groep14.ipsen2BE.DAO;
import nl.groep14.ipsen2BE.Models.Leftover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository
public interface LeftoverRepository extends JpaRepository<Leftover, Long> {
    List<Leftover> findByType(String type);
    ArrayList<Leftover> getAllByProcessed(boolean processed);
    ArrayList<Leftover> findAllByArtikelnummer(String artikelnummer);
}
