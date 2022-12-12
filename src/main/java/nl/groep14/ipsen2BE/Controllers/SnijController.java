package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Services.SnijService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * SnijController is the controller for the snijApplicatie. SnijController is used to check in which Category a certain Article should be placed.
 * @author Dino Yang
 */
@Controller
@RequestMapping(value = "/api/snij")
public class SnijController {
    private final SnijService snijService;
    public SnijController(SnijService snijService) {
        this.snijService = snijService;
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse snijApplicatie(){
        return snijService.snijApplication();
    }
    @RequestMapping(value = "/setup", method = RequestMethod.GET)
    @ResponseBody
    public String snijSetup(){
        for (int i = 0; i < 100; i++) {
            snijService.snijApplication();
        }
        return "Setup complete";
    }
}
