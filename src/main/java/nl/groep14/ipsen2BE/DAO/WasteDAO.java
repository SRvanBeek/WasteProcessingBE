package nl.groep14.ipsen2BE.DAO;
import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
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

    public Optional<Waste> getOrderByID(Long id){
        return this.wasteRepository.findById(id);
    }
}
