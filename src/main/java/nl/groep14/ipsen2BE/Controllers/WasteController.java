package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.DAO.WasteDAO;
import nl.groep14.ipsen2BE.Exceptions.OrderNotFoundException;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping(value = "/api/waste")
public class WasteController {


    private final WasteDAO wasteDAO;

    public WasteController(WasteDAO wasteDAO) {
        this.wasteDAO = wasteDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postArticle(@RequestBody Waste waste){
        this.wasteDAO.saveToDatabase(waste);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Waste> getAllArticles(){
        return this.wasteDAO.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Waste getOneArticle(@PathVariable Long id){
        return this.wasteDAO.getOrderByID(id).get();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse putOneWaste(@PathVariable Long id,@RequestBody Waste newWaste){
        Waste currentWaste = this.wasteDAO.getOrderByID(id).get();
        long currentID = currentWaste.getArtikelId();
        currentWaste = newWaste;
        currentWaste.setArtikelId(currentID);
        this.wasteDAO.saveToDatabase(currentWaste);
        return new ApiResponse(HttpStatus.ACCEPTED, "You updated article"+currentID+"!");
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> orderNotFound(
            OrderNotFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
