package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Leftover;
import nl.groep14.ipsen2BE.Models.Order;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author Dino Yang && Noah Elstgeest && Stijn van Beek
 */
@Component
public class LeftoverDAO {
    private final LeftoverRepository leftoverRepository;

    public LeftoverDAO(LeftoverRepository leftoverRepository) {
        this.leftoverRepository = leftoverRepository;
    }

    /**
     * getAll returns every CutWaste entity in the database.
     * @return Arraylist with ever CutWaste.
     */
    public ArrayList<Leftover> getAll() {
        return (ArrayList<Leftover>) this.leftoverRepository.findAll();
    }

    /**
     * saveToDatabase saves a cutWaste to the database.
     * @param leftover that needs to be saved.
     */
    public void saveToDatabase(Leftover leftover){
        this.leftoverRepository.save(leftover);
    }

    /**
     * getByType gets every cutWaste with a specified type.
     * @param type is the type of cutWastes that need to be pulled.
     * @return ArrayList with Cutwastes.
     */
    public ArrayList<Leftover> getByType(String type) {
        return (ArrayList<Leftover>) this.leftoverRepository.findByType(type);
    }

    /**
     * attempts to return a single CutWaste if one exists with the given id.
     * @param id the id that is used to find a CutWaste.
     * @return a single CutWaste if one exists with the given id.
     */
    public Leftover getById(long id) {
        Optional<Leftover> leftover = this.leftoverRepository.findById(id);
        return leftover.orElse(null);
    }

    /**
     * get all the leftovers with the given articlenumber
     * @param artikelnummer the given articlenumber
     * @return a leftover model
     */
    public ArrayList<Leftover> getByArtikelNummer(String artikelnummer) {
        ArrayList<Leftover> leftover = this.leftoverRepository.findAllByArtikelnummer(artikelnummer);
        return leftover;
    }

    public ArrayList<Leftover> getAllProcessed(boolean processed) {
        return this.leftoverRepository.getAllByProcessed(processed);
    }

    public void setLeftoverVisibilitytrueByID(Leftover leftover){
        leftover.setDisable(true);
        this.leftoverRepository.save(leftover);
    }
}
