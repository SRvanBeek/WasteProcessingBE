package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.LeftoverDAO;
import nl.groep14.ipsen2BE.Models.*;
import nl.groep14.ipsen2BE.Services.LeftoverService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author Dino Yang
 */
@Controller
@RequestMapping(value = "api/leftover")
public class LeftoverController {
    private final LeftoverDAO leftoverDAO;
    private final LeftoverService leftoverService;

    public LeftoverController(LeftoverDAO leftoverDAO, LeftoverService leftoverService) {
        this.leftoverDAO = leftoverDAO;
        this.leftoverService = leftoverService;
    }

    /**
     * getAllLeftover gets all the Leftover in the database.
     * @return an ApiResponse containing every Leftover entity in the database.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getAllLeftovers(){
        return new ApiResponse(HttpStatus.ACCEPTED, this.leftoverDAO.getAll());
    }

    @RequestMapping(value = "/byProcessed/{processed}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getAllLeftoversByProcessed(@PathVariable boolean processed){
        return new ApiResponse(HttpStatus.ACCEPTED, this.leftoverDAO.getAllProcessed(processed));
    }

    /**
     *getOneLeftover gets one Leftover entity from the database if it exists in the database. otherwise will return an 404.
     * @param id of the Leftover entity.
     * @return an ApiResponse from the leftover with the given ID.
     */
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getOneLeftover(@PathVariable long id){
        if(this.leftoverDAO.getById(id) == null){
            return new ApiResponse(HttpStatus.NOT_FOUND, "ID does not exist");
        }
        return new ApiResponse(HttpStatus.ACCEPTED, this.leftoverDAO.getById(id));
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
     *getLeftoverByType gets every Leftover with a specified type. if the type doesn't exist it returns a 404
     * @param type of Leftover.
     * @return ApiResponse with the Leftovers.
     */
    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getLeftoverByType(@PathVariable String type){
        if (this.leftoverDAO.getByType(type).isEmpty()){
            return new ApiResponse(HttpStatus.NOT_FOUND, "Type does not exist");
        }
        return new ApiResponse(HttpStatus.ACCEPTED, this.leftoverDAO.getByType(type));
    }

    @RequestMapping(value = "/customer/{customer}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getLeftoverByCustomer(@PathVariable String customer){
        return leftoverService.getLeftoverByCustomerId(customer);
    }

    /**
     * this functions disable an leftover with the given leftover model
     * @param leftover a model of leftover
     * @return an apiresponse that it worked
     */
    @RequestMapping(value = "/disable", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse disableOneLeftover(@RequestBody Leftover leftover) {
        this.leftoverDAO.setLeftoverVisibilitytrueByID(leftover);
        return new ApiResponse(HttpStatus.ACCEPTED, "You disabled order " + leftover + "!");
    }

}

