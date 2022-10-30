package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.CategoryDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/category")
public class CategoryController {
    private final CategoryDAO categoryDAO;

    public CategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse updateCategories(@RequestBody Category category) {
        if(this.categoryDAO.getCategoryByID(category.getId()).isEmpty()){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "category does not exist!");
        }

        this.categoryDAO.saveToDatabase(category);
        return new ApiResponse<>(HttpStatus.ACCEPTED, "category updated");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> categories(){
        return this.categoryDAO.getAll();
    }

}
