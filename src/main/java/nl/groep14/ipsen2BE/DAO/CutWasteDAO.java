package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Cutwaste;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Dino Yang && Noah Elstgeest && Stijn van Beek
 */
@Component
public class CutWasteDAO {
    private final CutWasteRepository cutWasteRepository;

    public CutWasteDAO(CutWasteRepository cutWasteRepository) {
        this.cutWasteRepository = cutWasteRepository;
    }

    /**
     * getAll returns every CutWaste entity in the database.
     * @return Arraylist with ever CutWaste.
     */
    public ArrayList<Cutwaste> getAll() {
        return (ArrayList<Cutwaste>) this.cutWasteRepository.findAll();
    }

    /**
     * saveToDatabase saves a cutWaste to the database.
     * @param cutwaste that needs to be saved.
     */
    public void saveToDatabase(Cutwaste cutwaste){
        this.cutWasteRepository.save(cutwaste);
    }

    /**
     * getByType gets every cutWaste with a specified type.
     * @param type is the type of cutWastes that need to be pulled.
     * @return ArrayList with Cutwastes.
     */
    public ArrayList<Cutwaste> getByType(String type) {
        return (ArrayList<Cutwaste>) this.cutWasteRepository.findByType(type);
    }

    /**
     * attempts to return a single CutWaste if one exists with the given id.
     * @param id the id that is used to find a CutWaste.
     * @return a single CutWaste if one exists with the given id.
     */
    public Cutwaste getById(long id) {
        Optional<Cutwaste> cutwaste = this.cutWasteRepository.findById(id);
        return cutwaste.orElse(null);
    }

    public Optional<Cutwaste> getByArtikelNummer(String artikelnummer) {
        return this.cutWasteRepository.getCutwasteByArtikelnummer(artikelnummer);
    }
}
