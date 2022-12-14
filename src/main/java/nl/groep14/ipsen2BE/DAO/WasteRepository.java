package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Order;
import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface WasteRepository extends JpaRepository<Waste,Long> {
    ArrayList<Waste> getAllByEnabled(boolean enabled);
    Optional<Waste> getWasteByCutWasteId(long cutwasteId);
    Optional<ArrayList<Waste>> getAllByCategoryIdAndEnabled(long categoryId, boolean enabled);

}
