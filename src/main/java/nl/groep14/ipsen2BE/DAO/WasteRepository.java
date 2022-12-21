package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface WasteRepository extends JpaRepository<Waste,Long> {
    ArrayList<Waste> getAllByEnabled(boolean enabled);
    Optional<Waste> getWasteByLeftoverId(long leftoverId);
    Optional<ArrayList<Waste>> getAllByCategoryIdAndEnabled(long categoryId, boolean enabled);

}
