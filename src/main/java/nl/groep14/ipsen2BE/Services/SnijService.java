package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.Controllers.LeftoverController;
import nl.groep14.ipsen2BE.DAO.*;
import nl.groep14.ipsen2BE.Models.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * SnijService is the service for the SnijController
 * @author Dino Yang
 */
@Service
public class SnijService {

    private final ArticleDAO articleDAO;
    private final CustomerDAO customerDAO;
    private final LeftoverDAO leftoverDAO;
    private final OrderDAO orderDAO;
    private final VoorraadDAO voorraadDAO;
    private final CategoryDAO categoryDAO;
    private final LeftoverController cw;

    private final WasteService wasteService;
    private final Random rand = new Random();
    public SnijService(ArticleDAO articleDAO, CustomerDAO customerDAO, OrderDAO orderDAO, LeftoverDAO leftoverDAO,
                       LeftoverController cw, VoorraadDAO voorraadDAO, WasteService wasteService, CategoryDAO categoryDAO) {
        this.articleDAO = articleDAO;
        this.customerDAO = customerDAO;
        this.orderDAO = orderDAO;
        this.leftoverDAO = leftoverDAO;
        this.cw = cw;
        this.voorraadDAO = voorraadDAO;
        this.wasteService = wasteService;
        this.categoryDAO = categoryDAO;
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
    public ApiResponse snijApplication(){
        Article chosenArticle = this.articleDAO.getRandomArticle();
        String customerId = chosenArticle.getLeverancier();
        Customer customer = this.customerDAO.getCustomerByID(customerId).get();
        int articleBreedte = chosenArticle.getStofbreedte();
        long articleGewicht = (long) chosenArticle.getGewicht();
        long metrage = this.rand.nextLong((articleBreedte / 3));
        long gewicht = (long) (articleGewicht / 100.0 * (100.0 / articleBreedte * metrage));
        double minMeter = customer.getMin_meter();
        double maxMeter = customer.getMax_meter();
        Leftover leftover = new Leftover(chosenArticle.getArtikelnummer(), false, metrage, gewicht, new Date());
        if (metrage > maxMeter) {
            leftover.setType("storage");
            this.cw.postLeftover(leftover);
            this.voorraadDAO.saveToDatabase(new Voorraad(leftover.getId(), null,false, null));
        } else if (metrage >= minMeter && metrage <= maxMeter) {
            leftover.setType("order");
            this.cw.postLeftover(leftover);
            this.orderDAO.saveToDatabase(new Order(leftover.getId(), null,false, null));
        } else {
            ArrayList<Category> catogories = this.categoryDAO.getAll();
            leftover.setType("catWaste");
            this.cw.postLeftover(leftover);
            wasteService.createAndSave(chosenArticle,catogories, leftover.getId() );
        }

        return new ApiResponse(HttpStatus.ACCEPTED, "Gesneden");
    }
}
