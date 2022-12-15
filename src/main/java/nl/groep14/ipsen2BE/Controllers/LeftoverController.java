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
     * getAllLeftover gets all the Leftover in the database.
     * @return ArrayList containing every Leftover entity in the database.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Leftover> getAllLeftovers(){
        return this.leftoverDAO.getAll();
    }

    /**
     *getOneLeftover gets one Leftover entity from the database.
     * @param id of the Leftover entity.
     * @return Leftover.
     */
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Leftover getOneLeftover(@PathVariable long id){
        return this.leftoverDAO.getById(id);
    }

    /**
     * postLeftover posts a Leftover in to the database.
     * @param leftover that needs to be posted.
     * @return ApiResponse with response.
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postLeftover(@RequestBody Leftover leftover){
        this.leftoverDAO.saveToDatabase(leftover);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted a Leftover!");
    }

    /**
     * PutLeftover puts a Leftover into the database
     * @param leftover that needs to be put.
     * @return ApiResponse with response.
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse perLeftover(@RequestBody Leftover leftover){
        this.leftoverDAO.saveToDatabase(leftover);
        return new ApiResponse(HttpStatus.ACCEPTED, "You've put a Leftover!");
    }

    /**
     *getLeftoverByType gets every Leftover with a specified type.
     * @param type of Leftover.
     * @return ArrayList with Leftover.
     */
    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Leftover> getLeftoverByType(@PathVariable String type){
        return this.leftoverDAO.getByType(type);
    }
}
