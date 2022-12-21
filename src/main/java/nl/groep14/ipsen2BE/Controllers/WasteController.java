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
    public ApiResponse<String> postWaste(@RequestBody Waste waste){
        this.wasteDAO.saveToDatabase(waste);
        return new ApiResponse<>(HttpStatus.ACCEPTED, "You posted some data!");
    }
    /**
     * putWaste puts a Waste into the database.
     * @param waste The WasteModel that is received in the PutMethod
     * @return ApiResponse with a corresponding message
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse<String> putWaste(@RequestBody Waste waste){
        this.wasteDAO.saveToDatabase(waste);
        return new ApiResponse<>(HttpStatus.ACCEPTED, "You've put some data!");
    }

    /**
     * getAllWaste gets all waste entities in the database.
     * @return ApiResponse of Waste entities
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Waste>> getAllWaste(){
        return new ApiResponse<>(HttpStatus.ACCEPTED, this.wasteDAO.getAll());
    }

    /**
     * getOneWaste gets one specific Waste entity based on the id
     * @param id is the id of the Waste entity
     * @return ApiResponse of Waste entity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getOneWaste(@PathVariable Long id){
        if (this.wasteDAO.getWasteByID(id).isPresent()) {
            return new ApiResponse<>(HttpStatus.ACCEPTED, this.wasteDAO.getWasteByID(id).get());
        } else {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Given Id does not exist in the database");
        }
    }

    /**
     * getOneWasteByleftoverId returns one Waste entity based on the leftover id of the Waste entity from the database.
     * @param leftoverId is the id of the leftover linked to the Waste entity
     * @return ApiResponse of Waste entity
     */
    @RequestMapping(value = "/perleftover/{leftoverId}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getOneWasteByLeftoverId(@PathVariable Long leftoverId){
        if (this.wasteDAO.getWasteByLeftoverId(leftoverId).isPresent()) {
            return new ApiResponse<>(HttpStatus.ACCEPTED, this.wasteDAO.getWasteByLeftoverId(leftoverId).get());
        } else {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Given leftoverId does not exist in the database");
        }
    }

    /**
     * returns the total weight and metrage of all categorized waste.
     * @return ApiResponse of weight and metrage of all categorized waste.
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<double[]> getTotalWasteDetails(){
        return new ApiResponse<>(HttpStatus.ACCEPTED, this.wasteFilterService.getTotalWaste());
    }

    /**
     * returns the total weight and metrage of all waste in the given category.
     *
     * @param categoryName the category to retrieve the details from.
     * @return ApiResponse of weight and metrage of all waste in a given category.
     */
    @RequestMapping(value = "/details/{categoryName}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getTotalWasteDetailsPerCategory(@PathVariable String categoryName){
        if (this.wasteFilterService.getTotalWastePerCategory(categoryName) != null) {
            return new ApiResponse<>(HttpStatus.ACCEPTED, this.wasteFilterService.getTotalWastePerCategory(categoryName));
        } else {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "No waste details for given category");
        }
    }

    /**
     * returns the composition and its weight of all waste in a given category
     * @param categoryName the category to retrieve the composition from.
     * @return ApiResponse of the composition of a given category.
     */
    @RequestMapping(value = "/composition/{categoryName}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getCompositionPerCategory(@PathVariable String categoryName){
        if (!this.wasteFilterService.getImpureCompositionPerCategory(categoryName).isEmpty()) {
            return new ApiResponse<>(HttpStatus.ACCEPTED, this.wasteFilterService.getImpureCompositionPerCategory(categoryName));
        } else {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "No compostions for given category");
        }
    }

}
