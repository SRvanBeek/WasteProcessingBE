package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.CategoryDAO;
import nl.groep14.ipsen2BE.DAO.CutWasteDAO;
import nl.groep14.ipsen2BE.DAO.WasteDAO;
import nl.groep14.ipsen2BE.Models.Category;
import nl.groep14.ipsen2BE.Models.Cutwaste;
import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WasteFilterService {
    CutWasteDAO cutWasteDAO;
    WasteDAO wasteDAO;
    CategoryDAO categoryDAO;

    public WasteFilterService(CutWasteDAO cutWasteDAO, WasteDAO wasteDAO, CategoryDAO categoryDAO) {
        this.cutWasteDAO = cutWasteDAO;
        this.wasteDAO = wasteDAO;
        this.categoryDAO = categoryDAO;
    }

    public double[] getTotalWaste() {
       ArrayList<Waste> allWaste = wasteDAO.getAll();

        return getWasteDetails(allWaste);
    }

    public double[] getTotalWastePerCategory(String categoryName) {
        long categoryId = getCategoryIdByName(categoryName);
        ArrayList<Waste> allWastePerCategory;

        if(wasteDAO.getWasteByCategoryId(categoryId).isPresent()){
            allWastePerCategory = wasteDAO.getWasteByCategoryId(categoryId).get();
        }
        else {
            return null;
        }

        return getWasteDetails(allWastePerCategory);
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

    public ArrayList<String> getCategoryNames(){
        ArrayList<Category> categories = categoryDAO.getAll();
        ArrayList<String> categoryNames = new ArrayList<>();

        for (Category category: categories
             ) {
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }
}
