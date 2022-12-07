package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.WasteDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * WasteController is the controller for the Waste Entity
 * @author Dino Yang
 */
@Controller
@RequestMapping(value = "/api/waste")
public class WasteController {
    private final WasteDAO wasteDAO;

    public WasteController(WasteDAO wasteDAO) {
        this.wasteDAO = wasteDAO;
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
     * getOneWasteByArticleId returns one Waste entity based on the Article id of the Waste entity from the database.
     * @param articleId is the id of the Article linked to the Waste entity
     * @return Waste entity
     */
    @RequestMapping(value = "/perArticle/{articleId}", method = RequestMethod.GET)
    @ResponseBody
    public Waste getOneWasteByArticleId(@PathVariable Long articleId){
        return this.wasteDAO.getWasteByCutWasteId(articleId).get();
    }

}
