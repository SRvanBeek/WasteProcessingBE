package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.VoorraadDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Voorraad;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


/**
 * @author Noah Elstgeest && Dino Yang
 */
@Controller
@RequestMapping(value = "/api/voorraad")
public class VoorraadController {
    private final VoorraadDAO voorraadDAO;

    public VoorraadController(VoorraadDAO voorraadDAO) {
        this.voorraadDAO = voorraadDAO;
    }

    /**
     * postVoorraad posts a Voorraad entity in the database.
     * @param voorraad entity that needs to be posted.
     * @return ApiResponse with response.
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<String> postVoorraad(@RequestBody Voorraad voorraad){
        this.voorraadDAO.saveToDatabase(voorraad);
        return new ApiResponse<>(HttpStatus.ACCEPTED, "You posted some data!");
    }

    /**
     * putVoorraad puts a Voorraad entity in the database.
     * @param voorraad entity that needs to be put.
     * @return ApiResponse with response.
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse<String> putVoorraad(@RequestBody Voorraad voorraad){
        this.voorraadDAO.saveToDatabase(voorraad);
        return new ApiResponse<>(HttpStatus.ACCEPTED, "You've put some data!");
    }

    /**
     * getVoorraadByIDByLeftoverID gets an voorraad by cutWastID
     * @return ApiResponse of everything from voorraad.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Voorraad>> getAllVoorraad(){
        return new ApiResponse<>(HttpStatus.ACCEPTED, this.voorraadDAO.getAll());
    }

    /**
     * getVoorraadByIDByLeftoverID gets an voorraad by cutWastID
     * @param id voorraad id.
     * @return ApiResponse of Voorraad entity.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getVoorraadByID(@PathVariable Long id){
        if (this.voorraadDAO.getVoorraadByID(id).isPresent()) {
            return new ApiResponse<>(HttpStatus.ACCEPTED, this.voorraadDAO.getVoorraadByID(id));
        } else {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "No voorraad found with this Id");
        }
    }

    /**
     * getVoorraadByIDByLeftoverID gets an voorraad by cutWastID
     * @param id leftoverID.
     * @return ApiResponse of Voorraad entity.
     */
    @RequestMapping(value = "/perLeftover/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getVoorraadByLeftoverID(@PathVariable Long id){
        if (this.voorraadDAO.getVoorraadByLeftoverId(id) != null) {
            return new ApiResponse<>(HttpStatus.ACCEPTED, this.voorraadDAO.getVoorraadByLeftoverId(id));
        } else {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "No voorraad found with this leftoverId");
        }
    }
}
