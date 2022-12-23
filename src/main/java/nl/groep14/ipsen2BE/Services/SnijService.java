package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.*;
import nl.groep14.ipsen2BE.Exceptions.NotFoundException;
import nl.groep14.ipsen2BE.Models.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * SnijService is the service for the SnijController
 * @author Dino Yang, Stijn van Beek
 */
@Service
public class SnijService {

    private final ArticleDAO articleDAO;
    private final CustomerDAO customerDAO;
    private final LeftoverDAO leftoverDAO;
    private final OrderDAO orderDAO;
    private final VoorraadDAO voorraadDAO;
    private final CategoryDAO categoryDAO;

    private final WasteService wasteService;
    private final Random rand = new Random();
    public SnijService(ArticleDAO articleDAO, CustomerDAO customerDAO, OrderDAO orderDAO, LeftoverDAO leftoverDAO,
                       VoorraadDAO voorraadDAO, WasteService wasteService, CategoryDAO categoryDAO) {
        this.articleDAO = articleDAO;
        this.customerDAO = customerDAO;
        this.orderDAO = orderDAO;
        this.leftoverDAO = leftoverDAO;
        this.voorraadDAO = voorraadDAO;
        this.wasteService = wasteService;
        this.categoryDAO = categoryDAO;
    }


    /**
     * attempts to add a leftover to the database when an article and metrage are given.
     * @param articleNumber the articlenumber used to generate leftovers.
     * @param metrageJSON the metrage value in String format.
     * @return an ApiResponse with a corresponding status code and message.
     */
    public ApiResponse<String> addLeftover(String articleNumber, String metrageJSON){
        if (this.articleDAO.getArticleByArtikelNummer(articleNumber).isEmpty()) {
            return new ApiResponse<>(HttpStatus.FORBIDDEN, "Article not in database");
        }

        double metrage;
        try {
             metrage = Double.parseDouble(metrageJSON);
        }
        catch (NumberFormatException nfe) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, "Please use a valid metrage value! (example: 25, 30.5");
        }

        try {
            Article article = this.articleDAO.getArticleByArtikelNummer(articleNumber).get();
            createLeftover((long) metrage, article, getCustomer(article));
            return new ApiResponse<>(HttpStatus.ACCEPTED, "Cut data loaded");
        }
        catch (NotFoundException nfe) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, nfe.getMessage());
        }
    }

    /**
     * attempts to add a given amount of random leftovers to the database.
     * It will return the actual amount of leftovers that have been added.
     * @param amount the amount of leftovers to be added.
     * @return the actual amount of leftovers that have been added.
     */
    public int addRandomLeftovers(int amount) {
        ArrayList<Article> articles = articleDAO.getAll();
        for (int i = 0; i < amount; i++) {
            Article article = articles.get(new Random().nextInt(articles.size()));
            double min = (double) article.getStofbreedte() / 10 * 0.1;
            double max = (double) article.getStofbreedte() / 10 * 0.5;
            int randomMetrage = (int) ((Math.random() * (max - min)) + min);
            try {
                createLeftover(randomMetrage, article, getCustomer(article));
            }
            catch (NotFoundException nfe) {
                return i;
            }
        }
        return amount;
    }

    /**
     * attempts to return the customer assigned to the given article, will throw a NotFoundException when no
     * customer exists in the database.
     * @param article the article to receive the customer from.
     * @return a Customer model when one exists.
     * @throws NotFoundException when the requested customer does not yet exist.
     */
    private Customer getCustomer(Article article) throws NotFoundException {
        String customerId = article.getLeverancier();

        if (this.customerDAO.getCustomerByID(customerId).isEmpty()) {
            throw new NotFoundException("No customer exists on the selected article!");
        }

        return this.customerDAO.getCustomerByID(customerId).get();
    }


    /**
     * creates a new leftover in the database based on the given article, the square metres of the leftover and the customer-settings
     * @param metrage the square metres of the leftover. This will be used to calculate the weight.
     * @param article the article used to generate the leftover
     * @param customer the customer from which the customer-settings are received
     */
    private void createLeftover(double metrage, Article article, Customer customer) {
        double minMeter = customer.getMin_meter();
        double maxMeter = customer.getMax_meter();
        Leftover leftover = new Leftover(article.getArtikelnummer(), false, metrage, calculateWeight(article, metrage), new Date());
        if (metrage > maxMeter) {
            leftover.setType("storage");
            this.leftoverDAO.saveToDatabase(leftover);
            this.voorraadDAO.saveToDatabase(new Voorraad(leftover.getId(), null,false, null));
        } else if (metrage >= minMeter && metrage <= maxMeter) {
            leftover.setType("order");
            this.leftoverDAO.saveToDatabase(leftover);
            this.orderDAO.saveToDatabase(new Order(leftover.getId(), null,false, null));
        } else {
            ArrayList<Category> categories = this.categoryDAO.getAll();
            leftover.setType("catWaste");
            this.leftoverDAO.saveToDatabase(leftover);
            wasteService.createAndSave(article, categories, leftover.getId());
        }
    }

    /**
     * calculates the waste of a leftover based on the given article and the given metrage (square metres)
     * @param article the article which is used to calculate the weight.
     * @param metrage the amount of square meters of which the leftover consists.
     * @return the weight of the leftover rounded to 2 decimals.
     */
    public double calculateWeight(Article article, double metrage) {
        DecimalFormat decfor = new DecimalFormat("0.00");

        double articleWeightPerMeter = article.getGewicht();
        double articleWidthInMeter = (double) article.getStofbreedte() / 100;
        double articleLengthInMeter = metrage / articleWidthInMeter;

        return Double.parseDouble(decfor.format(articleWeightPerMeter * articleLengthInMeter));
    }
}
