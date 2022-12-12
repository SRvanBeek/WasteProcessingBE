package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.CutWasteDAO;
import nl.groep14.ipsen2BE.DAO.OrderDAO;
import nl.groep14.ipsen2BE.DAO.VoorraadDAO;
import nl.groep14.ipsen2BE.DAO.WasteDAO;
import nl.groep14.ipsen2BE.Models.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * @author Dino Yang
 */
@Controller
@RequestMapping(value = "api/cutWaste")
public class CutWasteController {
    private final CutWasteDAO cutWasteDAO;

    public CutWasteController(CutWasteDAO cutWasteDAO) {
        this.cutWasteDAO = cutWasteDAO;
    }

    /**
     * getAllCutWaste gets all the cutWaste in the database.
     * @return ArrayList containing every CutWaste entity in the database.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Cutwaste> getAllCutWaste(){
        return this.cutWasteDAO.getAll();
    }

    /**
     *getOneCutWaste gets one CutWaste entity from the database.
     * @param id of the CutWaste entity.
     * @return CutWaste.
     */
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Cutwaste getOneCutWaste(@PathVariable long id){
        return this.cutWasteDAO.getByID(id).get();
    }

    /**
     * postCutWaste posts a CutWaste in to the database.
     * @param cutwaste that needs to be posted.
     * @return ApiResponse with response.
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postCutWaste(@RequestBody Cutwaste cutwaste){
        this.cutWasteDAO.saveToDatabase(cutwaste);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted a CutWaste!");
    }

    /**
     * PutCutWaste puts a CutWaste into the database
     * @param cutwaste that needs to be put.
     * @return ApiResponse with response.
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse PutCutWaste(@RequestBody Cutwaste cutwaste){
        this.cutWasteDAO.saveToDatabase(cutwaste);
        return new ApiResponse(HttpStatus.ACCEPTED, "You've put a CutWaste!");
    }

    /**
     *getCutWasteByType gets every cutWaste with a specified type.
     * @param type of CutWaste.
     * @return ArrayList with CutWaste.
     */
    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Cutwaste> getCutWasteByType(@PathVariable String type){
        return this.cutWasteDAO.getByType(type);
    }


//    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Cutwaste getCutWasteById(@PathVariable Long id){
//        return this.cutWasteDAO.getByID(id).get();
//    }

}
