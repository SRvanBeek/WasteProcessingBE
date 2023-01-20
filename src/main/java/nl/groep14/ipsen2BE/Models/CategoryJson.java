package nl.groep14.ipsen2BE.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryJson {
    long id;
    String name;
    HashMap<String, ArrayList<String>> conditions;
    boolean enabled;

    public CategoryJson(long id, String name, HashMap<String, ArrayList<String>> conditions, boolean enabled) {
        this.id = id;
        this.name = name;
        this.conditions = conditions;
        this.enabled = enabled;
    }

    public CategoryJson() {

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "CategoryJson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", conditions=" + conditions +
                ", enabled=" + enabled +
                '}';
    }
}
