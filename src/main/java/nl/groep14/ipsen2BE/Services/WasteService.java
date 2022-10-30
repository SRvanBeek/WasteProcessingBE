package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.WasteDAO;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Category;
import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
@Service
public class WasteService {

    private final WasteDAO wasteDAO;
    private boolean hundredPercent = false;

    public WasteService(WasteDAO wasteDAO) {
        this.wasteDAO = wasteDAO;
    }
    //createWaste takes the sammenstelling from an Article and checks if it matches with a category voorwaarde.
    public void createWaste(Article chosenArticle, ArrayList<Category> catogories, long metrage){
        ArrayList<String> acceptedCategoriesList = new ArrayList<>();
        String samenstelling = chosenArticle.getSamenstelling();
        HashMap<String, Integer> samenstellingMap = samenstellingSplitter(samenstelling);
        for (Category category : catogories) {
            if (checkVoorwaarde(samenstellingMap,category)) {
                acceptedCategoriesList.add(category.getName());
                if (hundredPercent){
                    System.out.println("yes");
                    String hundred = acceptedCategoriesList.get(acceptedCategoriesList.size() - 1);
                    acceptedCategoriesList.clear();
                    acceptedCategoriesList.add(hundred);
                    break;
                }
            }
        }
        String categories = String.join(",",acceptedCategoriesList);
        Waste waste = new Waste(chosenArticle.getArtikelId(),metrage,categories);
        wasteDAO.saveToDatabase(waste);
    }
    //samenstellingSplitter splits the String samenstelling and returns a hashmap with
    //material name as key and percentage as value.
    private HashMap<String, Integer> samenstellingSplitter(String samenstelling) {
        HashMap<String, Integer> samenstellingMap = new HashMap<>();
        String[] str = samenstelling.split(" ");
        for (int i = 0; i < str.length; i++) {
            if (str[i].contains("%")) {
                samenstellingMap.put(str[i + 1], parseInteger(str[i]));
            }
        }
        return samenstellingMap;
    }

    private int parseInteger(String intString) {
        intString = intString.substring(0, intString.length() - 1);
        return Integer.parseInt(intString);
    }
    //checkVoorwaarde splits the voorwaarde into 'deelVoorwaarde' so that we can check them separately.
    //We first split the voorwaarde by || into 'deelVoorwaarde'. Then we split the voorwaarde again if it contains a &&.
    //After splitting the voorwaarde we use voorwaardeFalseOrTrue().
    public Boolean checkVoorwaarde(HashMap<String, Integer> samenstellingMap, Category category) {
        String[] deelVoorwaarde = category.getVoorwaarde().split("\\|\\|");
        for (String s : deelVoorwaarde) {
            if (s.contains("&&")) {
                ArrayList<Boolean> reqList = new ArrayList<>();
                String[] voorwaardeEN = s.split("&&");
                for (String value : voorwaardeEN) {
                    reqList.add(voorwaardeFalseOrTrue(value, samenstellingMap));
                }
                if (isAllTrue(reqList)){
                    return true;
                }
            } else {
                if (voorwaardeFalseOrTrue(s, samenstellingMap)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAllTrue(ArrayList<Boolean> reqList) {
        for (boolean req : reqList) {
            if (!req) return false;
        }
        return true;
    }
    //voorwaardeFalseOrTrue does the actual check itself. This method separates the name of the material and
    //the value of the material in the voorwaarde. With use of the samenstellingMap, we can check whether the voorwaarde
    //is true or false.
    public Boolean voorwaardeFalseOrTrue(String deelVoorwaarde, HashMap<String, Integer> samenstellingMap) {
        if (deelVoorwaarde.contains("%")) {
            String[] deelVoorwaardeSplit = deelVoorwaarde.split(" ");
            for (int i = 0; i < deelVoorwaardeSplit.length; i++) {
                if (deelVoorwaardeSplit[i].contains("%")) {
                    String voorwaardeKey = deelVoorwaardeSplit[i + 1];
                    if (samenstellingMap.containsKey(voorwaardeKey)) {
                        String valueString = deelVoorwaardeSplit[i].substring(0, deelVoorwaardeSplit[i].length() - 1);
                        if (valueString.contains(">")) {
                            int value = Integer.parseInt(valueString.replace(">", ""));
                            if (samenstellingMap.get(voorwaardeKey) > value) {
                                return true;
                            }
                        } else if (valueString.contains("<")) {
                            int value = Integer.parseInt(valueString.replace("<", ""));
                            if (samenstellingMap.get(voorwaardeKey) < value) {
                                return true;
                            }
                        } else {
                            int value = Integer.parseInt(valueString);
                            if (samenstellingMap.get(voorwaardeKey) == value) {
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
            deelVoorwaarde = deelVoorwaarde.replace(" ", "");
            if (deelVoorwaarde.contains("+Overig")){
                deelVoorwaarde = deelVoorwaarde.replace("+Overig", "");
            }
            return samenstellingMap.containsKey(deelVoorwaarde);
        }
        return false;
    }
}
