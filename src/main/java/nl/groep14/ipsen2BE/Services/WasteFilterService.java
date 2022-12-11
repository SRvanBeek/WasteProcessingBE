package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.DAO.CategoryDAO;
import nl.groep14.ipsen2BE.DAO.CutWasteDAO;
import nl.groep14.ipsen2BE.DAO.WasteDAO;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Category;
import nl.groep14.ipsen2BE.Models.Cutwaste;
import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@Service
public class WasteFilterService {
    CutWasteDAO cutWasteDAO;
    WasteDAO wasteDAO;
    CategoryDAO categoryDAO;
    ArticleDAO articleDAO;

    public WasteFilterService(CutWasteDAO cutWasteDAO, WasteDAO wasteDAO, CategoryDAO categoryDAO, ArticleDAO articleDAO) {
        this.cutWasteDAO = cutWasteDAO;
        this.wasteDAO = wasteDAO;
        this.categoryDAO = categoryDAO;
        this.articleDAO = articleDAO;
    }

    public double[] getTotalWaste() {
       ArrayList<Waste> allWaste = wasteDAO.getAll();

        return getWasteDetails(allWaste);
    }

    public double[] getTotalWastePerCategory(String categoryName) {
        return getWasteDetails(getAllWastePerCategory(categoryName));
    }

    public ArrayList<String> getCategoryNames(){
        ArrayList<Category> categories = categoryDAO.getAll();
        ArrayList<String> categoryNames = new ArrayList<>();

        for (Category category: categories
             ) {
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }

    public ArrayList<String> getCompositionPerCategory(String categoryName) {
        ArrayList<Cutwaste> cutwastePerCategory = getCategorizedCutwaste(categoryName);
        HashMap<String, Double> totalWeightPerMaterial = new HashMap<>();
        ArrayList<String> materialWeightList = new ArrayList<>();

        for (Cutwaste cutwaste: cutwastePerCategory
             ) {

            Optional<Article> article = articleDAO.getArticleByArtikelNummer(cutwaste.getArtikelnummer());
            if (article.isPresent()) {
                ArrayList<String[]> compositionValues = getCompositionValues(article.get());
                for (String[] values : compositionValues
                ) {
                    double percentage = Double.parseDouble(values[1]);
                    double weight = cutwaste.getGewicht() * percentage / 100;

                    if (totalWeightPerMaterial.containsKey(values[0])) {
                        totalWeightPerMaterial.put(values[0], (totalWeightPerMaterial.get(values[0]) + weight));
                    }
                    else {
                        totalWeightPerMaterial.put(values[0], weight);
                    }
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
    private double[] getWasteDetails(ArrayList<Waste> allWastePerCategory) {
        double totalWeight = 0;
        double totalMetrage = 0;

        for (Waste waste: allWastePerCategory
        ) {
            Cutwaste cutWaste = cutWasteDAO.getById(waste.getId());
            totalWeight += cutWaste.getGewicht();
            totalMetrage += cutWaste.getMetrage();
        }
        return new double[]{totalWeight, totalMetrage};
    }

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

    private ArrayList<Waste> getAllWastePerCategory(String categoryName) {
        long categoryId = getCategoryIdByName(categoryName);
        ArrayList<Waste> allWastePerCategory = new ArrayList<>();

        if(wasteDAO.getWasteByCategoryId(categoryId).isPresent()){
            allWastePerCategory = wasteDAO.getWasteByCategoryId(categoryId).get();
        }
        return allWastePerCategory;
    }

    private ArrayList<Cutwaste> getCategorizedCutwaste(String categoryName) {
        ArrayList<Waste> allWastePerCategory = getAllWastePerCategory(categoryName);
        ArrayList<Cutwaste> cutwastePerCategory = new ArrayList<>();

        for (Waste waste : allWastePerCategory
        ) {
            cutwastePerCategory.add(cutWasteDAO.getById(waste.getId()));
        }
        return cutwastePerCategory;
    }
}
