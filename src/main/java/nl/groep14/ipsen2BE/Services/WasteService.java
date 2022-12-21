package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.LeftoverDAO;
import nl.groep14.ipsen2BE.DAO.WasteDAO;
import nl.groep14.ipsen2BE.DAO.ArticleDAO;
import nl.groep14.ipsen2BE.Models.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * WasteService is a Service for the entity Waste.
 * @author Dino Yang
 */
@Service
public class WasteService {

    private final WasteDAO wasteDAO;
    private final LeftoverDAO leftoverDAO;
    private final ArticleDAO articleDAO;
    private boolean hundredPercent = false;

    public WasteService(WasteDAO wasteDAO, LeftoverDAO leftoverDAO, ArticleDAO articleDAO) {
        this.wasteDAO = wasteDAO;
        this.leftoverDAO = leftoverDAO;
        this.articleDAO = articleDAO;
    }

    /**
     * createAndSave creates a waste entity using createWaste and saves it to the database.
     * @param chosenArticle The Article that needs to be checked
     * @param catogories A list of all the categories in the database
     *
     */
    public void createAndSave(Article chosenArticle, ArrayList<Category> catogories, long cutwasteID){
        Waste waste = createWaste(chosenArticle,catogories,cutwasteID);
        this.wasteDAO.saveToDatabase(waste);
    }

    /**
     * createWaste takes the 'samenstelling' from an Article and checks for every Category if it matches with the 'condition' of the category.
     * When a condition of a Category contains a hundred percent of a material and the 'samenstelling' matches with that 'condition'.
     * We only want to send that category back, we do this using the variable 'this.hundredPercent'.
     * We create a new 'Waste' entity after checking all the Categories and return it.
     * @param chosenArticle The Article that needs to be checked
     * @param catogories A list of all the categories in the database
     * @return created Waste entity
     */
    public Waste createWaste(Article chosenArticle, ArrayList<Category> catogories, long cutwasteID){
        ArrayList<Category> acceptedCategoriesList = new ArrayList<>();
        String samenstelling = chosenArticle.getSamenstelling();
        HashMap<String, Integer> samenstellingMap = samenstellingSplitter(samenstelling);
        for (Category category : catogories) {
            if (checkCondition(samenstellingMap,category)) {
                acceptedCategoriesList.add(category);
                if (this.hundredPercent){
                    Category hundred = acceptedCategoriesList.get(acceptedCategoriesList.size() - 1);
                    acceptedCategoriesList.clear();
                    acceptedCategoriesList.add(hundred);
                    break;
                }
            }
        }
        return new Waste(cutwasteID,acceptedCategoriesList.get(0).getId(),null,false,null);
    }

    /**
     * samenstellingSplitter splits the String 'samenstelling' and returns a hashmap with material name as key and percentage as value.
     * @param samenstelling A String of the 'samenstelling' of an Article
     * @return A hashmap with material as key and percentage as value.
     */
    //
    private HashMap<String, Integer> samenstellingSplitter(String samenstelling) {
        HashMap<String, Integer> samenstellingMap = new HashMap<>();
        samenstelling = samenstelling.replace("/", " ");
        String[] str = samenstelling.split(" ");

        for (int i = 0; i < str.length; i++) {
            if (str[i].contains("%")) {
                samenstellingMap.put(str[i + 1], parseStringToInt(str[i]));
            }
        }
        return samenstellingMap;
    }

    /**
     * parseStringToInt changes a String into an int
     * @param intString String that contains an int
     * @return int that contains the parsed String
     */
    private int parseStringToInt(String intString) {
        intString = intString.substring(0, intString.length() - 1);
        return Integer.parseInt(intString);
    }


    /**
     * checkCondition splits the 'condition' of a Category into 'conditionParts' so that we can check them separately.
     * We first split the 'condition' by || into 'conditionParts'. Then we split the 'conditionParts' again if it contains an AND-character, into 'conditionAND'.
     * After splitting the 'condition' we use conditionFalseOrTrue() to see if the 'conditionParts' matches with the samenstelling.
     * @param samenstellingMap A hashmap with String material as key and int percentage as value.
     * @param category the Category we are checking
     * @return We return True when the samenstelling matches one of the 'conditionParts'.
     * If there are any AND-characters in the 'conditionParts', every condition in String[]'conditionAND' need to match the samenstelling.
     */
    public Boolean checkCondition(HashMap<String, Integer> samenstellingMap, Category category) {
        String[] conditionParts = category.getVoorwaarde().split("\\|\\|");
        for (String conditionPart : conditionParts) {
            if (conditionPart.contains("&&")) {
                ArrayList<Boolean> reqList = new ArrayList<>();
                String[] conditionAND = conditionPart.split("&&");
                for (String value : conditionAND) {
                    reqList.add(conditionFalseOrTrue(value, samenstellingMap));
                }
                if (areAllTrue(reqList)){
                    return true;
                }
            } else {
                if (conditionFalseOrTrue(conditionPart, samenstellingMap)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * areAllTrue checks whether all the booleans in a given arrayList are true or not.
     * @param reqList The arrayList to check
     * @return We return true if all the booleans in the arrayList are true
     */
    private boolean areAllTrue(ArrayList<Boolean> reqList) {
        for (boolean req : reqList) {
            if (!req) return false;
        }
        return true;
    }


    /**
     * conditionFalseOrTrue does the actual check itself. This method separates the name of the material and
     * the value of the material in the condition and checks whether the condition is true or false given
     * the samenstelling of an Article. When the samenstelling matches a condition which is a hundred percent of a  material
     * this.hundredPercent becomes true. We do this because when the samenstelling matches a condition of a category
     * which is a hundred percent of a material, we only want to send that particular category back.
     * @param conditionPart The condition we are checking
     * @param samenstellingMap A hashmap with String material as key and int percentage as value.
     * @return A True is returned when the condition matches with the samenstelling.
     */
    public Boolean conditionFalseOrTrue(String conditionPart, HashMap<String, Integer> samenstellingMap) {
        if (conditionPart.contains("%")) {
            String[] conditionPartsSplit = conditionPart.split(" ");
            for (int i = 0; i < conditionPartsSplit.length; i++) {
                if (conditionPartsSplit[i].contains("%")) {
                    String conditionKey = conditionPartsSplit[i + 1];
                    if (samenstellingMap.containsKey(conditionKey)) {
                        String valueString = conditionPartsSplit[i].substring(0, conditionPartsSplit[i].length() - 1);
                        if (valueString.contains(">")) {
                            int value = Integer.parseInt(valueString.replace(">", ""));
                            if (samenstellingMap.get(conditionKey) > value) {
                                return true;
                            }
                        } else if (valueString.contains("<")) {
                            int value = Integer.parseInt(valueString.replace("<", ""));
                            if (samenstellingMap.get(conditionKey) < value) {
                                return true;
                            }
                        } else {
                            int value = Integer.parseInt(valueString);
                            if (samenstellingMap.get(conditionKey) == value) {
                                if (value == 100){
                                    this.hundredPercent = true;
                                }
                                return true;
                            }
                        }

                    }
                }
            }
        } else {
            conditionPart = conditionPart.replace(" ", "");
            if (conditionPart.contains("+Overig")){
                conditionPart = conditionPart.replace("+Overig", "");
            }
            return samenstellingMap.containsKey(conditionPart);
        }
        return false;
    }

    public ApiResponse getArticleByWasteId(Long id){
        Waste waste = wasteDAO.getWasteByID(id).get();
        Leftover leftover = leftoverDAO.getById(waste.getLeftoverId());
        return new ApiResponse(HttpStatus.ACCEPTED, articleDAO.getArticleByArtikelNummer(leftover.getArtikelnummer()));

    }
}
