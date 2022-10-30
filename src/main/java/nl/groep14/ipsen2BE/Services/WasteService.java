package nl.groep14.ipsen2BE.Services;

import nl.groep14.ipsen2BE.DAO.WasteDAO;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Category;
import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
@Service
public class WasteService {

    private final WasteDAO wasteDAO;

    public WasteService(WasteDAO wasteDAO) {
        this.wasteDAO = wasteDAO;
    }

    public void createWaste(Article chosenArticle, ArrayList<Category> catogories, long metrage){
        ArrayList<String> acceptedCategoriesList = new ArrayList<>();
        String samenstelling = chosenArticle.getSamenstelling();

        HashMap<String, Integer> samenstellingMap = samenstellingSplitter(samenstelling);
        for (Category category : catogories) {
            if (checkVoorwaarde(samenstellingMap,category)) {
                acceptedCategoriesList.add(category.getName());
            }
        }
        String categories = String.join(",",acceptedCategoriesList);
        Waste waste = new Waste(chosenArticle.getArtikelId(),metrage,categories);
        wasteDAO.saveToDatabase(waste);
    }

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
