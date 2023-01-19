package nl.groep14.ipsen2BE.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryJson {
    long id;
    String name;
    HashMap<String, ArrayList<String>> conditions;

    public CategoryJson(long id, String name, HashMap<String, ArrayList<String>> conditions) {
        this.id = id;
        this.name = name;
        this.conditions = conditions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, ArrayList<String>> getConditions() {
        return conditions;
    }

    public void setConditions(HashMap<String, ArrayList<String>> conditions) {
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        return "CategoryJson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Conditions=" + conditions +
                '}';
    }
}
