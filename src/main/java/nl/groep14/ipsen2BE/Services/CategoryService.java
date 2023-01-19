package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.CategoryDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Category;
import nl.groep14.ipsen2BE.Models.CategoryJson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;



@Service
public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public ApiResponse getCategoryByID(long id) {
        Optional<Category> category = this.categoryDAO.getCategoryByID(id);
        if (category.isEmpty()) {
            return new ApiResponse(HttpStatus.NOT_FOUND, "Category with id: " + id + " does not exist!");
        }

        HashMap<String, ArrayList<String>> map = getSeparateConditions(category.get());

        return new ApiResponse<>(HttpStatus.ACCEPTED, new CategoryJson(category.get().getId(), category.get().getName(), map));
    }

    /**
     * returns the separate conditions as a hashmap.
     *
     * @param category the category used to split the condition.
     * @return Hashmap with separate conditions/
     */
    public HashMap<String, ArrayList<String>> getSeparateConditions(Category category) {
        String[] conditions = category.getVoorwaarde().split("\\|\\|");
        HashMap<String, ArrayList<String>> conditionsMap = new HashMap<>();
        for (String condition : conditions) {
            System.out.println(condition);
            if (!condition.contains("&&") && !condition.contains("+")) {
                String[] c = condition.split("%");

                ArrayList<String> array = new ArrayList<>();
                array.add(c[0].trim());

                conditionsMap.put(c[1].trim(), array);
            }

            else if (condition.contains("+") && !condition.contains("&&") && !condition.contains("%")) {
                String[] c = condition.split("\\+");
                if (conditionsMap.get(c[0].trim()) == null) {
                    ArrayList<String> array = new ArrayList<>();
                    array.add(c[1].trim());
                    System.out.println(array);
                    conditionsMap.put(c[0].trim(), array);
                }
                else {
                    ArrayList<String> array = conditionsMap.get(c[0].trim());
                    array.add(c[1].trim());
                    System.out.println(array);
                    conditionsMap.put(c[0].trim(), array);
                }
            }

            else {
                String[] cAnd = condition.split("&&");

                for (String part: cAnd) {
                    String[] c = part.split("%");


                    if (conditionsMap.get(c[1].trim()) == null) {
                        ArrayList<String> array = new ArrayList<>();
                        array.add(c[0].trim());
                        System.out.println(array);
                        conditionsMap.put(c[1].trim(), array);
                    }
                    else {
                        ArrayList<String> array = conditionsMap.get(c[1].trim());
                        array.add(c[0].trim());
                        System.out.println(array);
                        conditionsMap.put(c[1].trim(), array);
                    }
                }
            }
        }
        System.out.println(conditionsMap);
        return conditionsMap;
    }

    public boolean noOverlap(CategoryJson categoryJson) {


        return true;
    }
}
