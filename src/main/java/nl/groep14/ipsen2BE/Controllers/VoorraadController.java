package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.VoorraadDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Voorraad;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postVoorraad(@RequestBody Voorraad voorraad){
        this.voorraadDAO.saveToDatabase(voorraad);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }

    /**
     * putVoorraad puts a Voorraad entity in the database.
     * @param voorraad entity that needs to be put.
     * @return ApiResponse with response.
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse putVoorraad(@RequestBody Voorraad voorraad){
        this.voorraadDAO.saveToDatabase(voorraad);
        return new ApiResponse(HttpStatus.ACCEPTED, "You've put some data!");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Voorraad> getAllWaste(){
        return this.voorraadDAO.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Voorraad getVoorraadByID(@PathVariable Long id){
        return this.voorraadDAO.getVoorraadByID(id).get();
    }

    /**
     * getVoorraadByIDByLeftoverID gets an voorraad by cutWastID
     * @param id leftoverID.
     * @return Voorraad entity.
     */
    @RequestMapping(value = "/perLeftover/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Voorraad getVoorraadByIDByLeftoverID(@PathVariable Long id){
        return this.voorraadDAO.getVoorraadByLeftoverId(id);
    }
}
