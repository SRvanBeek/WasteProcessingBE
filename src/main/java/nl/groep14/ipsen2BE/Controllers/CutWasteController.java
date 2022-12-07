package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.CutWasteDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Cutwaste;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "api/cutWaste")
public class CutWasteController {
    private final CutWasteDAO cutWasteDAO;

    public CutWasteController(CutWasteDAO cutWasteDAO) {
        this.cutWasteDAO = cutWasteDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Cutwaste> getAllCutWaste(){
        return this.cutWasteDAO.getAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postCutWaste(@RequestBody Cutwaste cutwaste){
        this.cutWasteDAO.saveToDatabase(cutwaste);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted a CutWaste!");
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Cutwaste> getCutWasteByType(@PathVariable String type){
        return this.cutWasteDAO.getByType(type);
    }
}
