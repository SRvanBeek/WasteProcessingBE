package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.CategoryDAO;
import nl.groep14.ipsen2BE.DAO.CustomerDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Category;
import nl.groep14.ipsen2BE.Models.Customer;
import nl.groep14.ipsen2BE.Services.WasteFilterService;
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
    private final WasteFilterService wasteFilterService;

    /**
     * Class Constructer, initializes the CategoryDAO
     *
     * @param categoryDAO        The CategoryDAO that will be initialized in this Controller
     * @param wasteFilterService
     */
    public CategoryController(CategoryDAO categoryDAO, WasteFilterService wasteFilterService) {
        this.categoryDAO = categoryDAO;
        this.wasteFilterService = wasteFilterService;
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
     * Adds the given Customer to the database using the saveToDataBase method in the CustomerDAO.
     *
     * @param category The CategoryModel that is received in the POST-Request body.
     * @return The newly added Category.
     *
     * @see CustomerDAO#saveToDatabase(Customer)
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Category addCategory(@RequestBody Category category) {

        this.categoryDAO.saveToDatabase(category);
        return category;
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
     * @param id The id used to find the specific Category.
     * @return The requested Category.
     * @see CategoryDAO#getCategoryByID(Long)
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Category> getCategoryByID(@PathVariable long id){
        Optional<Category> category = this.categoryDAO.getCategoryByID(id);
        return category;
    }

    /**
     * Attempts to delete a Category with the given id if this Category exists.
     * @param id The id used to find and delete the specific Category.
     * @return ApiResponse with 200 OK and a message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ApiResponse deleteCategoryByID(@PathVariable Long id){
        this.categoryDAO.deleteById(id);
        return new ApiResponse(HttpStatus.ACCEPTED, "You deleted category "+id+"!");
    }

    /**
     * returns a list with every category name that exists in the database.
     * @return an Arraylist with category names.
     */
    @RequestMapping(value = "/names", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<String> categoryNames(){
        return this.wasteFilterService.getCategoryNames();
    }

    @RequestMapping(value = "/names/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getCategoryNameByID(@PathVariable long id){
        Category category = this.categoryDAO.getCategoryByID(id).get();
        System.out.println(category.getName());
        return category.getName();
    }

}
