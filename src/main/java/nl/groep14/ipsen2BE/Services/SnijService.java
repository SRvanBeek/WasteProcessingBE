package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.Controllers.ArticleController;
import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.DAO.CategoryDAO;
import nl.groep14.ipsen2BE.DAO.CustomerDAO;
import nl.groep14.ipsen2BE.DAO.OrderDAO;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Category;
import nl.groep14.ipsen2BE.Models.Customer;
import nl.groep14.ipsen2BE.Models.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

/**
 * SnijService is the service for the SnijController
 * @author Dino Yang
 */
@Service
public class SnijService {

    private final ArticleDAO articleDAO;
    private final CustomerDAO customerDAO;
    private final WasteService wasteService;
    private final CategoryDAO categoryDAO;
    private final OrderDAO orderDAO;

    private final ArticleController ac;

    private final Random rand = new Random();

    public SnijService(ArticleDAO articleDAO, CustomerDAO customerDAO, WasteService wasteService,
                       CategoryDAO categoryDAO, OrderDAO orderDAO, ArticleController ac) {
        this.articleDAO = articleDAO;
        this.customerDAO = customerDAO;
        this.wasteService = wasteService;
        this.categoryDAO = categoryDAO;
        this.orderDAO = orderDAO;
        this.ac = ac;
    }

    /**
     * snijApplication is used to simulate the snijApplication.
     * First we choose a random Article who's userId is null. Then we randomly generate a 'metrage', we do
     * this to simulate the snijApplicatie.
     * After generating the 'metrage', we check in which category the article fits. This depends on the min
     * and max meter of the Customer linked to the Article.
     * @return String containing 'type, articleID' type is here the either an order, voorraad or waste
     * depending on the min and max settings of the customer and articleID is the id of the article.
     */
    public String snijApplication(){
        Article chosenArticle = articleDAO.getRandomArticle();
        int articleBreedte = chosenArticle.getBreedte();
        long metrage = this.rand.nextLong((articleBreedte / 3));
        long customerID = chosenArticle.getCustomerId();
        Customer customer = this.customerDAO.getCustomerByID(customerID).get();
        double minMeter = customer.getMin_meter();
        double maxMeter = customer.getMax_meter();
        ArrayList<Category> catogories = this.categoryDAO.getAll();
        chosenArticle.setUserId(1);
        this.ac.putOneArticle(chosenArticle.getArtikelId(),chosenArticle);
        if (metrage > maxMeter) {
            return "Voorraad, " + chosenArticle.getArtikelId();
        } else if (metrage >= minMeter && metrage <= maxMeter) {
            Order order = new Order(customerID,chosenArticle.getArtikelId(),metrage, true);
            this.orderDAO.saveToDatabase(order);
            return "Order, " + chosenArticle.getArtikelId();
        } else {
            wasteService.createAndSave(chosenArticle,catogories, metrage);
            return "Waste, " + chosenArticle.getArtikelId();
        }
    }
}
