package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Services.SnijService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse snijApplicatie(@RequestBody Map<String, String> payload){
        System.out.println(payload);
        return snijService.addLeftover((String) payload.get("articleNumber"), payload.get("metrage"));
    }
    @RequestMapping(value = "/random/{amount}", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<String> snijSetup(@PathVariable int amount){
        int actualAmount = snijService.addRandomLeftovers(amount);

        if (actualAmount == amount) {
            return new ApiResponse<>(HttpStatus.ACCEPTED, (amount + " leftovers added!"));
        }
        else {
            return new ApiResponse<>(HttpStatus.CONFLICT, "only " + actualAmount + " leftovers added before something went wrong");
        }
    }
}
