package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.DAO.CategoryDAO;
import nl.groep14.ipsen2BE.DAO.CustomerDAO;
import nl.groep14.ipsen2BE.DAO.WasteDAO;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Category;
import nl.groep14.ipsen2BE.Models.Customer;
import nl.groep14.ipsen2BE.Services.WasteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Random;

@Controller
@RequestMapping(value = "/api/snij")
public class SnijController {

    private final ArticleDAO articleDAO;
    private final CustomerDAO customerDAO;
    private final WasteService wasteService;
    private final CategoryDAO categoryDAO;

    private final Random rand = new Random();

    public SnijController(ArticleDAO articleDAO, CustomerDAO customerDAO, WasteDAO wasteDAO, WasteService wasteService, CategoryDAO categoryDAO) {
        this.articleDAO = articleDAO;
        this.customerDAO = customerDAO;
        this.wasteService = wasteService;
        this.categoryDAO = categoryDAO;
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String snijApplicatie(){
        Article chosenArticle = articleDAO.getRandomArticle();
        int articleBreedte = chosenArticle.getBreedte();
        int metrage = this.rand.nextInt((articleBreedte / 3));
        long customerID = chosenArticle.getCustomerId();
        Customer customer = this.customerDAO.getCustomerByID(customerID).get();
        float minMeter = customer.getMinMeter();
        float maxMeter = customer.getMaxMeter();
        ArrayList<Category> catogories = this.categoryDAO.getAll();
        if (metrage > maxMeter) {
            //gooi weg
            System.out.println("VOORAAD");
        } else if (metrage >= minMeter && metrage <= maxMeter) {
            //maak verstuur op label
            System.out.println("verstuur op");
        } else {
            wasteService.createWaste(chosenArticle,catogories, metrage);
            String response = "Waste, " + chosenArticle.getArtikelId();
            return response;
        }
        return "ERROR";
    }



}
