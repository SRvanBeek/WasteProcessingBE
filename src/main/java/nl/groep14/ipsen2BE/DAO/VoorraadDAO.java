package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Voorraad;
import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class VoorraadDAO {

    private final VoorraadRepository voorraadRepository;

    public VoorraadDAO(VoorraadRepository voorraadRepository) {
        this.voorraadRepository = voorraadRepository;
    }

    public void saveToDatabase(Voorraad voorraad){
        this.voorraadRepository.save(voorraad);
    }

    public ArrayList<Voorraad> getAll(){
        return (ArrayList<Voorraad>) this.voorraadRepository.findAll();
    }

    public Optional<Voorraad> getVoorraadByID(Long id){
        return this.voorraadRepository.findById(id);
    }
}
