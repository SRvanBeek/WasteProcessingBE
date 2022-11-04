package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Data Access Object for managing Categories in the database.
 * @author Stijn van Beek
 */
@Component
public class CategoryDAO {
    private final CategoryRepository categoryRepository;

    /**
     * Class Constructor, initializes the CategoryRepository.
     * @param categoryRepository The CategoryRepository to be initialized.
     */
    public CategoryDAO(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Adds the given Category to the database, or updates a Category when this method is called with a PUT-Request
     * @param category The Category that gets added or updated in the database.
     */
    public void saveToDatabase(Category category){
        this.categoryRepository.save(category);
    }

    /**
     * Gets every Category from the database and returns it as an ArrayList
     *
     * @return An ArrayList with every Category in the database
     */
    public ArrayList<Category> getAll(){
        return (ArrayList<Category>) this.categoryRepository.findAll();
    }

    /**
     * Attempts to return a single Category if one exists in the database with the given id.
     *
     * @param id The id that is used to find a specific Category.
     * @return A Category if a Category with the id exists.
     */
    public Optional<Category> getCategoryByID(Long id){
        return this.categoryRepository.findById(id);
    }

    /**
     * Deletes a specific category in the database based on the given id.
     * @param id the target Category to be deleted
     */
    public void deleteById(long id){
        this.categoryRepository.deleteById(id);
    }
}
