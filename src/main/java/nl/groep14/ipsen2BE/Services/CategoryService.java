package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.CategoryDAO;
import nl.groep14.ipsen2BE.Exceptions.CategoryOutOfBoundsException;
import nl.groep14.ipsen2BE.Exceptions.CategoryOverlapException;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Category;
import nl.groep14.ipsen2BE.Models.CategoryJson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;



@Service
public class CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    /**
     * returns a Category JSON object, which is the same as a normal category Entity,
     * but the conditions are seperated into its own values.
     *
     * @param id the id of the category to be received.
     * @return an ApiResponse with a Category with separate conditions.
     */
    public ApiResponse<CategoryJson> getCategoryByID(long id) {
        Optional<Category> category = this.categoryDAO.getCategoryByID(id);
        if (category.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "Category with id: " + id + " does not exist!");
        }

        HashMap<String, ArrayList<String>> map = getSeparateConditions(category.get());

        return new ApiResponse<>(HttpStatus.ACCEPTED, new CategoryJson(category.get().getId(), category.get().getName(), map, category.get().isEnabled()));
    }

    /**
     * attempts to save a new category to the database. will fail if overlap is detected with other categories,
     * or when the submitted values are below 0 or above 100.
     *
     * @param categoryJson the category Json object to be converted into a category entity and saved.
     * @return an ApiResponse with the corresponding statusCode and message
     */
    public ApiResponse<String> saveCategory(CategoryJson categoryJson) {
        try {
            if (noOverlap(categoryJson, false)) {
                this.categoryDAO.saveToDatabase(new Category(categoryJson.getName(), combineSeparateConditions(categoryJson.getConditions()), categoryJson.isEnabled()));
            }
        } catch (CategoryOverlapException | CategoryOutOfBoundsException e) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ApiResponse<>(HttpStatus.ACCEPTED, "category added: " + categoryJson.getName());
    }

    /**
     * attempts to update an existing category in the database. will fail if overlap is detected with other categories,
     * or when the submitted values are below 0 or above 100.
     *
     * @param categoryJson the category Json object to be converted into a category entity and updated.
     * @return an ApiResponse with the corresponding statusCode and message.
     */
    public ApiResponse<String> updateCategory(CategoryJson categoryJson) {
        Optional<Category> category = this.categoryDAO.getCategoryByID(categoryJson.getId());
        if (category.isEmpty()) {
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "category does not exist!");
        }
        try {
            if (noOverlap(categoryJson, true)) {
                this.categoryDAO.saveToDatabase(new Category(categoryJson.getId(), categoryJson.getName(), combineSeparateConditions(categoryJson.getConditions()), categoryJson.isEnabled()));
            }
        } catch (CategoryOverlapException | CategoryOutOfBoundsException e) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return new ApiResponse<>(HttpStatus.ACCEPTED, "category updated: " + categoryJson.getName());
    }

    /**
     * combines a hashmap of conditions into a single string which so that the conditions can be added to the database..
     *
     * @param conditions hashmap of conditions of a CategoryJson object.
     * @return a String with the conditions according to the given data-set.
     */
    public String combineSeparateConditions(HashMap<String, ArrayList<String>> conditions) {
        StringBuilder conditionStringBuilder = new StringBuilder();

        for (String key : conditions.keySet()) {
            for (int i = 0; i < conditions.get(key).size(); i++) {

                conditionStringBuilder.append(conditions.get(key).get(i)).append("% ").append(key);
                if (!(i >= conditions.get(key).size() - 1)) {
                    conditionStringBuilder.append(" && ");
                }
            }
            conditionStringBuilder.append(" || ");
        }
        return conditionStringBuilder.substring(0, conditionStringBuilder.length() - 4);
    }


    /**
     * returns the separate conditions as a hashmap.
     *
     * @param category the category used to split the condition.
     * @return Hashmap with separate conditions.
     */
    public HashMap<String, ArrayList<String>> getSeparateConditions(Category category) {
        String[] conditions = category.getVoorwaarde().split("\\|\\|");
        HashMap<String, ArrayList<String>> conditionsMap = new HashMap<>();
        for (String condition : conditions) {
            if (!condition.contains("&&") && !condition.contains("+")) {
                String[] c = condition.split("%");
                ArrayList<String> array = new ArrayList<>();
                array.add(c[0].trim());
                conditionsMap.put(c[1].trim(), array);
            } else if (condition.contains("+") && !condition.contains("&&") && !condition.contains("%")) {
                String[] c = condition.split("\\+");
                if (conditionsMap.get(c[0].trim()) == null) {
                    ArrayList<String> array = new ArrayList<>();
                    array.add(c[1].trim());
                    conditionsMap.put(c[0].trim(), array);
                } else {
                    ArrayList<String> array = conditionsMap.get(c[0].trim());
                    array.add(c[1].trim());
                    conditionsMap.put(c[0].trim(), array);
                }
            } else {
                String[] cAnd = condition.split("&&");

                for (String part : cAnd) {
                    String[] c = part.split("%");

                    if (conditionsMap.get(c[1].trim()) == null) {
                        ArrayList<String> array = new ArrayList<>();
                        array.add(c[0].trim());
                        conditionsMap.put(c[1].trim(), array);
                    } else {
                        ArrayList<String> array = conditionsMap.get(c[1].trim());
                        array.add(c[0].trim());
                        conditionsMap.put(c[1].trim(), array);
                    }
                }
            }
        }
        return conditionsMap;
    }

    /**
     * returns true if no overlap is detected on existing categories or throws CategoryOverlapException when there is
     * overlap
     *
     * @param categoryJson the Category Json object to check for overlapping methods
     * @param putRequest   boolean value which checks if the request method is put or not, if it is PUT,
     *                     then the overlap for the given category id is ignored
     * @return true if no overlap is detected
     * @throws CategoryOverlapException with the category-name where overlap is detected
     */
    public boolean noOverlap(CategoryJson categoryJson, boolean putRequest) throws CategoryOverlapException {
        ArrayList<Category> categories = categoryDAO.getAll();

        for (Category category : categories) {
            HashMap<String, ArrayList<String>> conditions = getSeparateConditions(category);

            for (String key : conditions.keySet()
            ) {
                if (!categoryJson.getConditions().containsKey(key)) {
                    break;
                } else {
                    int min = Integer.MIN_VALUE;
                    int max = Integer.MAX_VALUE;
                    boolean hundredPercent = false;

                    for (String value : conditions.get(key)) {
                        if (value.contains("Overig")) {
                            continue;
                        } else if (value.contains(">")) {
                            min = Integer.parseInt(value.replace(">", "").trim());
                        } else if (value.contains("<")) {
                            max = Integer.parseInt(value.replace("<", "").trim());
                        } else if (Integer.parseInt(value.trim()) == 100) {
                            hundredPercent = true;
                        }
                    }

                    int newValueMin = Integer.MIN_VALUE;
                    int newValueMax = Integer.MAX_VALUE;
                    boolean newHundredPercent = false;

                    for (String value : categoryJson.getConditions().get(key)) {
                        int numericVal = Integer.parseInt(value.replaceAll("[> <]", ""));
                        if (numericVal > 100 || numericVal < 0) {
                            throw new CategoryOutOfBoundsException();
                        } else if (!hundredPercent && value.contains(">")) {
                            newValueMin = Integer.parseInt(value.replace(">", "").trim());
                        } else if (!hundredPercent && value.contains("<")) {
                            newValueMax = Integer.parseInt(value.replace("<", "").trim());
                        } else {
                            try {
                                newHundredPercent = (Integer.parseInt(value.trim()) == 100);
                            } catch (NumberFormatException ignored) {
                            }
                        }
                    }

                    if (putRequest && categoryJson.getId() == category.getId()) {
                        break;
                    }

                    if (hundredPercent && newHundredPercent) {
                        throw new CategoryOverlapException(category.getName());
                    }

                    if (!hundredPercent && !newHundredPercent && (!((newValueMin <= max) && (newValueMax <= min)) && !((newValueMin >= max) && (newValueMax >= max)))) {
                        throw new CategoryOverlapException(category.getName());
                    }
                }
            }
        }
        return true;
    }
}
