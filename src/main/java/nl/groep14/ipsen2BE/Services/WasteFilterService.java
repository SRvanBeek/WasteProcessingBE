package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.DAO.CategoryDAO;
import nl.groep14.ipsen2BE.DAO.LeftoverDAO;
import nl.groep14.ipsen2BE.DAO.WasteDAO;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Category;
import nl.groep14.ipsen2BE.Models.Leftover;
import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author Stijn van Beek
 */
@Service
public class WasteFilterService {
    LeftoverDAO leftoverDAO;
    WasteDAO wasteDAO;
    CategoryDAO categoryDAO;
    ArticleDAO articleDAO;

    public WasteFilterService(LeftoverDAO leftoverDAO, WasteDAO wasteDAO, CategoryDAO categoryDAO, ArticleDAO articleDAO) {
        this.leftoverDAO = leftoverDAO;
        this.wasteDAO = wasteDAO;
        this.categoryDAO = categoryDAO;
        this.articleDAO = articleDAO;
    }

    /**
     * returns the total weight and metrage of all categorized waste.
     * @return total weight and metrage as array.
     */
    public double[] getTotalWaste() {
       ArrayList<Waste> allWaste = wasteDAO.getAll();

        return getWasteDetails(allWaste);
    }

    /**
     * returns the total weight and metrage of all categorized waste on a given category.
     * @param categoryName
     * @return total weight and metrage as array of a given category.
     */
    public double[] getTotalWastePerCategory(String categoryName) {
        return getWasteDetails(getAllWastePerCategory(categoryName));
    }

    /**
     * returns a list of every category name in the database
     * @return arraylist with every category name
     */
    public ArrayList<String> getCategoryNames(){
        ArrayList<Category> categories = categoryDAO.getAll();
        ArrayList<String> categoryNames = new ArrayList<>();

        for (Category category: categories
             ) {
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }


    /**
     * returns the impure composition based on a given category. An impure material is for example: '50% PL/50% PES'
     * @param categoryName the given category to retrieve the composition from.
     * @return an ArrayList with every impure material in the given category and its weight as a string.
     */
    public ArrayList<String> getImpureCompositionPerCategory(String categoryName) {
        ArrayList<Leftover> leftoverPerCategory = getCategorizedLeftovers(categoryName);
        HashMap<String, Double> totalWeightPerMaterial = new HashMap<>();
        ArrayList<String> materialWeightList = new ArrayList<>();

        for (Leftover leftover : leftoverPerCategory
        ) {
            Optional<Article> article = articleDAO.getArticleByArtikelNummer(leftover.getArtikelnummer());
            if (article.isPresent()) {
                String composition = article.get().getSamenstelling();

                if (totalWeightPerMaterial.containsKey(composition)) {
                    totalWeightPerMaterial.put(composition, totalWeightPerMaterial.get(composition) + leftover.getGewicht());
                }
                else {
                    totalWeightPerMaterial.put(composition, (double) leftover.getGewicht());
                }
            }
        }
        for (String key: totalWeightPerMaterial.keySet()
        ) {
            String materialValueString = key + ": " + totalWeightPerMaterial.get(key);
            materialWeightList.add(materialValueString);
        }
        return materialWeightList;
    }

    /**
     * returns the values from the composition string so that they can be used for calculations.
     * @param article the article from which the composition string is received.
     * @return an arraylist of String arrays, with each array containing the pure material and its percentage.
     */
    private ArrayList<String[]> getCompositionValues(Article article) {
        ArrayList<String[]> compositionValues = new ArrayList<>();

        String composition = article.getSamenstelling();
        String[] parts = composition.trim().split("/");

        for (String part: parts
             ) {
            String[] keyValue = part.trim().split("%");

            String[] compositionPercentage = new String[]{keyValue[1].trim(), (keyValue[0].trim())};
            compositionValues.add(compositionPercentage);
        }
        return compositionValues;
    }

    /**
     * returns a double array with the total weight and metrage of a given list of Waste.
     *
     * @param wasteList the list of waste from which the weight and metrage are calculated
     * @return a double array with the total weight and metrage.
     */
    private double[] getWasteDetails(ArrayList<Waste> wasteList) {
        double totalWeight = 0;
        double totalMetrage = 0;

        for (Waste waste: wasteList
        ) {
            Leftover cutWaste = leftoverDAO.getById(waste.getLeftoverId());
            totalWeight += cutWaste.getGewicht();
            totalMetrage += cutWaste.getMetrage();
        }
        return new double[]{totalWeight, totalMetrage};
    }

    /**
     * returns the designated category id from a given category name.
     * @param categoryName the given category name.
     * @return a category id as a long.
     */
    private long getCategoryIdByName(String categoryName) {
        ArrayList<Category> categories = categoryDAO.getAll();

        for (Category category: categories
        ) {
            if (category.getName().equals(categoryName)) {
                return category.getId();
            }
        }
        return 0;
    }

    /**
     * returns an ArrayList of Waste based on a given category name.
     * @param categoryName the given category name.
     * @return an arraylist with Waste.
     */
    private ArrayList<Waste> getAllWastePerCategory(String categoryName) {
        long categoryId = getCategoryIdByName(categoryName);
        ArrayList<Waste> allWastePerCategory = new ArrayList<>();

        if(wasteDAO.getWasteByCategoryId(categoryId).isPresent()){
            allWastePerCategory = wasteDAO.getWasteByCategoryId(categoryId).get();
        }
        return allWastePerCategory;
    }

    /**
     * returns an ArrayList of leftovers based on a given category name.
     * @param categoryName the given category name.
     * @return an arraylist with Leftovers.
     */
    private ArrayList<Leftover> getCategorizedLeftovers(String categoryName) {
        ArrayList<Waste> allWastePerCategory = getAllWastePerCategory(categoryName);
        ArrayList<Leftover> leftoverPerCategory = new ArrayList<>();

        for (Waste waste : allWastePerCategory
        ) {
            leftoverPerCategory.add(leftoverDAO.getById(waste.getLeftoverId()));
        }
        return leftoverPerCategory;
    }
}