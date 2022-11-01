package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.CategoryDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.Optional;

/**
 * Spring Controller for handling Category-related Requests
 * @author Stijn van Beek
 */
@Controller
@RequestMapping(value = "/api/categories")
public class CategoryController {
    private final CategoryDAO categoryDAO;

    /**
     * Class Constructer, initializes the CategoryDAO
     * @param categoryDAO The CategoryDAO that will be initialized in this Controller
     */
    public CategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    /**
     * Checks if the given Category's ID exists in the database. When it does exist the given Category is updated
     * into the database using the saveToDatabase method in the CategoryDAO
     *
     * @param category The CategoryModel that is received in the PUT-Request body
     * @return ApiResponse with a corresponding message
     * @see CategoryDAO#saveToDatabase(Category)
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse updateCategory(@RequestBody Category category) {
        if(this.categoryDAO.getCategoryByID(category.getId()).isEmpty()){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "category does not exist!");
        }

        this.categoryDAO.saveToDatabase(category);
        return new ApiResponse<>(HttpStatus.ACCEPTED, "category updated");
    }

    /**
     * Gets all Categories from the database using the getAll method from the CategoryDAO.
     * The categories are returned as an ArrayList.
     *
     * @return An ArrayList with every Category in the database.
     * @see CategoryDAO#getAll()
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Category> categories(){
        ArrayList<Category> categories = this.categoryDAO.getAll();
        return categories;
    }

    /**
     * Attempts to return a single Category that contains the given ID from the database using the getCategoryByID
     * method from the CategoryDAO .
     *
     * @param id The id acquired from the @RequestMapping annotation
     * @return The requested Category.
     * @see CategoryDAO#getCategoryByID(Long)
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Category> getCategoryByID(@PathVariable long id){
        Optional<Category> category = this.categoryDAO.getCategoryByID(id);
        return category;
    }

}
