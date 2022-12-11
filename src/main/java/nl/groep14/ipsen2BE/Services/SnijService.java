package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.Controllers.CutWasteController;
import nl.groep14.ipsen2BE.DAO.*;
import nl.groep14.ipsen2BE.Models.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

/**
 * SnijService is the service for the SnijController
 * @author Dino Yang
 */
@Service
public class SnijService {

    private final ArticleDAO articleDAO;
    private final CustomerDAO customerDAO;
    private final CutWasteDAO cutWasteDAO;
    private final OrderDAO orderDAO;
    private final VoorraadDAO voorraadDAO;
    private final CategoryDAO categoryDAO;
    private final CutWasteController cw;

    private final WasteService wasteService;
    private final Random rand = new Random();
    public SnijService(ArticleDAO articleDAO, CustomerDAO customerDAO, OrderDAO orderDAO, CutWasteDAO cutWasteDAO,
                       CutWasteController cw, VoorraadDAO voorraadDAO, WasteService wasteService, CategoryDAO categoryDAO) {
        this.articleDAO = articleDAO;
        this.customerDAO = customerDAO;
        this.orderDAO = orderDAO;
        this.cutWasteDAO = cutWasteDAO;
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
    //  long metrage = this.rand.nextLong((articleBreedte / 3));
        long metrage = 0;
        long gewicht = (long) (articleGewicht / 100.0 * (100.0 / articleBreedte * metrage));
        double minMeter = customer.getMin_meter();
        double maxMeter = customer.getMax_meter();
        Cutwaste cutwaste = new Cutwaste(chosenArticle.getArtikelnummer(), false, metrage, gewicht);
        String artikelNummer = cutwaste.getArtikelnummer();
        if (metrage > maxMeter) {
            cutwaste.setType("Voorraad");
            this.cw.postCutWaste(cutwaste);
            this.voorraadDAO.saveToDatabase(new Voorraad(cutwaste.getId(), null,false, null));
        } else if (metrage >= minMeter && metrage <= maxMeter) {
            cutwaste.setType("Order");
            this.cw.postCutWaste(cutwaste);
            this.orderDAO.saveToDatabase(new Order(cutwaste.getId(), null,false, null));
        } else {
            ArrayList<Category> catogories = this.categoryDAO.getAll();
            cutwaste.setType("Afval");
            this.cw.postCutWaste(cutwaste);
            wasteService.createAndSave(chosenArticle,catogories, cutwaste.getId() );
        }


//
//        if (Objects.equals(cutwaste.getType(), "Voorraad")) {
//
//        } else if (Objects.equals(cutwaste.getType(), "Order")) {
//            Order order = new Order(cutWasteDAO.getByArtikelNummer(artikelNummer).get().getId(), 32);;
//            this.orderDAO.saveToDatabase(order);
//        } else {
//            this.wasteService.createAndSave(chosenArticle, categoryDAO.getAll(),
//                    cutWasteDAO.getByArtikelNummer(artikelNummer).get().getId(), 32);
//        }

        return new ApiResponse(HttpStatus.ACCEPTED, "Gesneden");
    }
}
