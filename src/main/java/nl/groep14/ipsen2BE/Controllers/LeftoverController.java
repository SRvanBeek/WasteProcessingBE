package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.LeftoverDAO;
import nl.groep14.ipsen2BE.Models.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author Dino Yang
 */
@Controller
@RequestMapping(value = "api/leftover")
public class LeftoverController {
    private final LeftoverDAO leftoverDAO;

    public LeftoverController(LeftoverDAO leftoverDAO) {
        this.leftoverDAO = leftoverDAO;
    }

    /**
     * getAllCutWaste gets all the cutWaste in the database.
     * @return ArrayList containing every CutWaste entity in the database.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Leftover> getAllLeftovers(){
        return this.leftoverDAO.getAll();
    }

    /**
     *getOneCutWaste gets one CutWaste entity from the database.
     * @param id of the CutWaste entity.
     * @return CutWaste.
     */
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Leftover getOneLeftover(@PathVariable long id){
        return this.leftoverDAO.getById(id);
    }

    /**
     * postCutWaste posts a CutWaste in to the database.
     * @param leftover that needs to be posted.
     * @return ApiResponse with response.
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postLeftover(@RequestBody Leftover leftover){
        this.leftoverDAO.saveToDatabase(leftover);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted a CutWaste!");
    }

    /**
     * PutCutWaste puts a CutWaste into the database
     * @param leftover that needs to be put.
     * @return ApiResponse with response.
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse perLeftover(@RequestBody Leftover leftover){
        this.leftoverDAO.saveToDatabase(leftover);
        return new ApiResponse(HttpStatus.ACCEPTED, "You've put a CutWaste!");
    }

    /**
     *getCutWasteByType gets every cutWaste with a specified type.
     * @param type of CutWaste.
     * @return ArrayList with CutWaste.
     */
    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Leftover> getLeftoverByType(@PathVariable String type){
        return this.leftoverDAO.getByType(type);
    }
}
