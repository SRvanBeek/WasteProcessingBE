package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Order;
import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface WasteRepository extends JpaRepository<Waste,Long> {
    @Query(value = "SELECT w from Waste w where w.cutWasteId = :cutWasteId")
    Optional<Waste> getWasteByCutWasteId(@Param("cutWasteId") long cutWasteId);

    @Query(value = "SELECT w from Waste w where w.categoryId = :categoryId")
    Optional<ArrayList<Waste>> getWasteByCategoryId(@Param("categoryId") long categoryId);

//    ArrayList<Waste> getAllByEnabled(boolean enabled);
//    Optional<Waste> getWasteByCutwasteId(long cutwasteId);
//    Optional<ArrayList<Waste>> getAllByCategoryIdAndEnabled(long categoryId, boolean enabled);

}
