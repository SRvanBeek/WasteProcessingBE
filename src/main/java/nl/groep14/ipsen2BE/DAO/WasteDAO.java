package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Optional;

/**
 * WasteDAO is the DAO of the Waste entity
 * @author Dino Yang
 */
@Component
public class WasteDAO {

    private final WasteRepository wasteRepository;

    public WasteDAO(WasteRepository wasteRepository) {
        this.wasteRepository = wasteRepository;
    }

    /**
     * Saves a single Waste to the database.
     * @param waste the Waste that is to be saved
     */
    public void saveToDatabase(Waste waste){
        this.wasteRepository.save(waste);
    }

    /**
     * gets all Waste in the database.
     * @return arrayList of Waste
     */
    public ArrayList<Waste> getAll(){
        return this.wasteRepository.getAllByEnabled(true);
    }
    /**
     * Attempts to return a single Waste if one exists in the database with the given id.
     * @param id The id that is used to find a specific Waste.
     * @return A Waste if a Waste with the id exists.
     */
    public Optional<Waste> getWasteByID(Long id){
        return this.wasteRepository.findById(id);
    }

    /**
     * Attempts to return a single Waste if one exists in the database with the given Article id.
     * @param id The Article id that is used to find a specific Waste.
     * @return A Waste if a Waste with the Article id exists.
     */
    public Optional<Waste> getWasteByCutWasteId(Long id){
        return this.wasteRepository.getWasteByCutwasteId(id);
    }
    /**
     * Attempts to return a List of Waste if at least one exists in the database with the given Category id.
     * @param id The Category id that is used to find specific Waste.
     * @return An arraylist of Waste if at least one Waste with the cateory id exists.
     */
    public Optional<ArrayList<Waste>> getWasteByCategoryId(Long id){
        return this.wasteRepository.getAllByCategoryIdAndEnabled(id, true);
    }

}
