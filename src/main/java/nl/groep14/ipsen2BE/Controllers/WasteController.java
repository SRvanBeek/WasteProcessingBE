package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.WasteDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Waste;
import nl.groep14.ipsen2BE.Services.WasteFilterService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * WasteController is the controller for the Waste Entity
 * @author Dino Yang
 */
@Controller
@RequestMapping(value = "/api/waste")
public class WasteController {
    private final WasteDAO wasteDAO;
    private final WasteFilterService wasteFilterService;

    public WasteController(WasteDAO wasteDAO, WasteFilterService wasteFilterService) {
        this.wasteDAO = wasteDAO;
        this.wasteFilterService = wasteFilterService;
    }

    /**
     * postWaste posts a Waste into the database.
     * @param waste The WasteModel that is received in the PostMethod
     * @return ApiResponse with a corresponding message
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postWaste(@RequestBody Waste waste){
        this.wasteDAO.saveToDatabase(waste);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }
    /**
     * putWaste puts a Waste into the database.
     * @param waste The WasteModel that is received in the PutMethod
     * @return ApiResponse with a corresponding message
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse putWaste(@RequestBody Waste waste){
        this.wasteDAO.saveToDatabase(waste);
        return new ApiResponse(HttpStatus.ACCEPTED, "You've put some data!");
    }

    /**
     * getAllWaste gets all waste entities in the database.
     * @return List of Waste entities
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Waste> getAllWaste(){
        return this.wasteDAO.getAll();
    }

    /**
     * getOneWaste gets one specific Waste entity based on the id
     * @param id is the id of the Waste entity
     * @return Waste entity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Waste getOneWaste(@PathVariable Long id){
        return this.wasteDAO.getWasteByID(id).get();
    }

    /**
     * getOneWasteByleftoverId returns one Waste entity based on the leftover id of the Waste entity from the database.
     * @param leftoverId is the id of the leftover linked to the Waste entity
     * @return Waste entity
     */
    @RequestMapping(value = "/perleftover/{leftoverId}", method = RequestMethod.GET)
    @ResponseBody
    public Waste getOneWasteByLeftoverId(@PathVariable Long leftoverId){
        return this.wasteDAO.getWasteByLeftoverId(leftoverId).get();
    }

    /**
     * returns the total weight and metrage of all categorized waste.
     * @return weight and metrage of all categorized waste.
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    @ResponseBody
    public double[] getTotalWasteDetails(){
        return this.wasteFilterService.getTotalWaste();
    }

    /**
     * returns the total weight and metrage of all waste in the given category.
     *
     * @param categoryName the category to retrieve the details from.
     * @return weight and metrage of all waste in a given category.
     */
    @RequestMapping(value = "/details/{categoryName}", method = RequestMethod.GET)
    @ResponseBody
    public double[] getTotalWasteDetailsPerCategory(@PathVariable String categoryName){
        return this.wasteFilterService.getTotalWastePerCategory(categoryName);
    }

    /**
     * returns the composition and its weight of all waste in a given category
     * @param categoryName the category to retrieve the composition from.
     * @return the composition of a given category.
     */
    @RequestMapping(value = "/composition/{categoryName}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<String> getCompositionPerCategory(@PathVariable String categoryName){
        return this.wasteFilterService.getImpureCompositionPerCategory(categoryName);
    }

}
