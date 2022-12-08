package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Cutwaste;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class CutWasteDAO {
    private final CutWasteRepository cutWasteRepository;

    public CutWasteDAO(CutWasteRepository cutWasteRepository) {
        this.cutWasteRepository = cutWasteRepository;
    }

    public ArrayList<Cutwaste> getAll() {
        return (ArrayList<Cutwaste>) this.cutWasteRepository.findAll();
    }

    public void saveToDatabase(Cutwaste cutwaste){
        this.cutWasteRepository.save(cutwaste);
    }

    public ArrayList<Cutwaste> getByType(String type) {
        return (ArrayList<Cutwaste>) this.cutWasteRepository.findByType(type);
    }

    public Cutwaste getById(long id) {
        Optional<Cutwaste> cutwaste = this.cutWasteRepository.findById(id);
        return cutwaste.orElse(null);

    }
}
