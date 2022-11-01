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

    public void saveToDatabase(Waste waste){
        this.wasteRepository.save(waste);
    }

    public ArrayList<Waste> getAll(){
        return (ArrayList<Waste>) this.wasteRepository.findAll();
    }

    public Optional<Waste> getWasteByID(Long id){
        return this.wasteRepository.findById(id);
    }

    public Optional<Waste> getWasteByOrderID(Long id){
        return this.wasteRepository.getWasteByArtikelId(id);
    }

}
