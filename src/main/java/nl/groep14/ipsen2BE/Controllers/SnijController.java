package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Services.SnijService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


/**
 * SnijController is the controller for the snijApplicatie. SnijController is used to check in which Category a certain Article should be placed.
 * @author Dino Yang
 */
@Controller
@RequestMapping(value = "/api/generateLeftovers")
public class SnijController {
    private final SnijService snijService;
    public SnijController(SnijService snijService) {
        this.snijService = snijService;
    }

    /**
     *
     * @param payload the body of the request. This method will attempt to get the articlenumber and the metrage from this payload.
     * @return an ApiResponse with the corresponding message and status code received from the service.
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<String> snijApplicatie(@RequestBody Map<String, String> payload){
        return snijService.addLeftover(payload.get("articleNumber"), payload.get("metrage"));
    }

    /**
     * generates a given amount of random leftovers with a max of 100 at a time.
     * @param amount the amount of random leftovers to be generated.
     * @return an ApiResponse with the amount of random leftovers that have been added.
     */
    @RequestMapping(value = "/random/{amount}", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<String> snijSetup(@PathVariable int amount){
        if (amount > 100) {
            return new ApiResponse<>(HttpStatus.FORBIDDEN, "amount cannot be greater than 100!");
        }

        int actualAmount = snijService.addRandomLeftovers(amount);

        if (actualAmount == amount) {
            return new ApiResponse<>(HttpStatus.ACCEPTED, (amount + " leftovers added!"));
        }
        else {
            return new ApiResponse<>(HttpStatus.CONFLICT, "only " + actualAmount + " leftovers added before something went wrong");
        }
    }
}
