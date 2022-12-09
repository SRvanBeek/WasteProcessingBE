package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.Controllers.CutWasteController;
import nl.groep14.ipsen2BE.DAO.*;
import nl.groep14.ipsen2BE.Models.*;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * SnijService is the service for the SnijController
 * @author Dino Yang
 */
@Service
public class SnijService {

    private final ArticleDAO articleDAO;
    private final CustomerDAO customerDAO;
    private final CutWasteController cw;
    private final Random rand = new Random();
    public SnijService(ArticleDAO articleDAO, CustomerDAO customerDAO, CutWasteController cw) {
        this.articleDAO = articleDAO;
        this.customerDAO = customerDAO;
        this.cw = cw;
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
        Article chosenArticle = articleDAO.getRandomArticle();
        String customerId = chosenArticle.getLeverancier();
        Customer customer = this.customerDAO.getCustomerByID(customerId).get();
        int articleBreedte = chosenArticle.getStofbreedte();
        long articleGewicht = (long) chosenArticle.getGewicht();
        long metrage = this.rand.nextLong((articleBreedte / 3));
        long gewicht = this.rand.nextLong((articleGewicht / 3));
        double minMeter = customer.getMin_meter();
        double maxMeter = customer.getMax_meter();
        Cutwaste cutwaste = new Cutwaste(chosenArticle.getArtikelnummer(), false, metrage, gewicht);
        if (metrage > maxMeter) {
            cutwaste.setType("Voorraad");
        } else if (metrage >= minMeter && metrage <= maxMeter) {
            cutwaste.setType("Order");
        } else {
            cutwaste.setType("Afval");
        }
        return this.cw.postCutWaste(cutwaste);
    }
}
