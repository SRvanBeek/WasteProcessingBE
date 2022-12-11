package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.VoorraadDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Voorraad;
import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/perCutWaste/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Voorraad getVoorraadByIDByCutWasteID(@PathVariable Long id){
        return this.voorraadDAO.getVoorraadByCutWasteId(id);
    }
}
