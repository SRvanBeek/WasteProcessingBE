package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.*;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Category;
import nl.groep14.ipsen2BE.Models.Customer;
import nl.groep14.ipsen2BE.Models.Order;
import nl.groep14.ipsen2BE.Services.WasteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Random;


/**
 * SnijController is the controller for the snijApplicatie. SnijController is used to check in which Category a certain Article should be placed.
 * @author Dino Yang
 */
@Controller
@RequestMapping(value = "/api/snij")
public class SnijController {

    private final ArticleDAO articleDAO;
    private final CustomerDAO customerDAO;
    private final WasteService wasteService;
    private final CategoryDAO categoryDAO;
    private final OrderDAO orderDAO;

    private final Random rand = new Random();

    public SnijController(ArticleDAO articleDAO, CustomerDAO customerDAO, WasteService wasteService, CategoryDAO categoryDAO, OrderDAO orderDAO) {
        this.articleDAO = articleDAO;
        this.customerDAO = customerDAO;
        this.wasteService = wasteService;
        this.categoryDAO = categoryDAO;
        this.orderDAO = orderDAO;
    }

    /**
     * snijApplicatie is the method called when one consumes the snij application of the api.
     * First we choose a random Article who's userId is null. Then we randomly generate a 'metrage', we do this to simulate the snijApplicatie.
     * After generating the 'metrage', we check in which category the article fits. This depends on the min and max meter of the Customer linked to the Article.
     * @return String containing 'type, articleID' type is here the either an order, voorraad or waste
     * depending on the min and max settings of the customer and articleID is the id of the article.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String snijApplicatie(){
        Article chosenArticle = articleDAO.getRandomArticle();
        int articleBreedte = chosenArticle.getBreedte();
        int metrage = this.rand.nextInt((articleBreedte / 3));
        long customerID = chosenArticle.getCustomerId();
        Customer customer = this.customerDAO.getCustomerByID(customerID).get();
        double minMeter = customer.getMin_meter();
        double maxMeter = customer.getMax_meter();

        ArrayList<Category> catogories = this.categoryDAO.getAll();
        if (metrage > maxMeter) {
            return "Voorraad, " + chosenArticle.getArtikelId();
        } else if (metrage >= minMeter && metrage <= maxMeter) {
            //maak verstuur op label
            Order order = new Order(customerID,chosenArticle.getArtikelId(),metrage);
            this.orderDAO.saveToDatabase(order);
            return "Order, " + chosenArticle.getArtikelId();
        } else {
            wasteService.createWaste(chosenArticle,catogories, metrage);
            return "Waste, " + chosenArticle.getArtikelId();
        }
    }



}
