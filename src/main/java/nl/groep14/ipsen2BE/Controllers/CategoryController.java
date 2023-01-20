package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.CategoryDAO;
import nl.groep14.ipsen2BE.DAO.CustomerDAO;
import nl.groep14.ipsen2BE.Exceptions.CategoryOverlapException;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Category;
import nl.groep14.ipsen2BE.Models.CategoryJson;
import nl.groep14.ipsen2BE.Models.Customer;
import nl.groep14.ipsen2BE.Services.CategoryService;
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
    private final CategoryService categoryService;
    private final WasteFilterService wasteFilterService;

    /**
     * Class Constructer, initializes the CategoryDAO and WasteFilterService
     *
     * @param categoryDAO        The CategoryDAO that will be initialized in this Controller.
     * @param categoryService
     * @param wasteFilterService the WasteFilterService to be initialized.
     */
    public CategoryController(CategoryDAO categoryDAO, CategoryService categoryService, WasteFilterService wasteFilterService) {
        this.categoryDAO = categoryDAO;
        this.categoryService = categoryService;
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
    public ApiResponse<String> updateCategory(@RequestBody CategoryJson category) {
        return this.categoryService.updateCategory(category);
    }

    /**
     * Adds the given Customer to the database using the saveToDataBase method in the CustomerDAO.
     *
     * @param category The CategoryModel that is received in the POST-Request body.
     * @return ApiResponse with a corresponding message.
     * @see CustomerDAO#saveToDatabase(Customer)
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<String> addCategory(@RequestBody CategoryJson category) {
        return this.categoryService.saveCategory(category);
    }

    /**
     * Gets all Categories from the database using the getAll method from the CategoryDAO.
     * The categories are returned as an ArrayList.
     *
     * @return An ApiResponse with an ArrayList with every Category in the database as payload.
     * @see CategoryDAO#getAll()
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Category>> categories() {
        ArrayList<Category> categories = this.categoryDAO.getAll();
        return new ApiResponse<>(HttpStatus.ACCEPTED, categories);
    }

    /**
     * Attempts to return a single Category that contains the given ID from the database using the getCategoryByID
     * method from the CategoryDAO.
     *
     * @param id The id used to find the specific Category.
     * @return An ApiResponse with the requested Category as payload if it exists.
     * @see CategoryDAO#getCategoryByID(Long)
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getCategoryByID(@PathVariable long id) {
        return this.categoryService.getCategoryByID(id);
    }

    /**
     * Attempts to delete a Category with the given id if this Category exists.
     *
     * @param id The id used to find and delete the specific Category.
     * @return ApiResponse with a corresponding message.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ApiResponse<String> deleteCategoryByID(@PathVariable Long id) {
        this.categoryDAO.deleteById(id);
        return new ApiResponse<>(HttpStatus.ACCEPTED, "You deleted category " + id + "!");
    }

    /**
     * returns a list with every category name that exists in the database.
     *
     * @return an ApiResponse with an Arraylist of category names as payload.
     */
    @RequestMapping(value = "/names", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<String>> categoryNames() {
        return new ApiResponse<>(HttpStatus.ACCEPTED, this.wasteFilterService.getCategoryNames());
    }

    /**
     * attempts to return the category name when a category-id is provided.
     * @param id the id of the target category.
     * @return an ApiResponse with the category name as payload if the target category exists.
     */
    @RequestMapping(value = "/names/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<String> getCategoryNameByID(@PathVariable long id) {
        Optional<Category> category = this.categoryDAO.getCategoryByID(id);
        if (category.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "category does not exist!");
        }
        return new ApiResponse<>(HttpStatus.ACCEPTED, category.get().getName());
    }
}
