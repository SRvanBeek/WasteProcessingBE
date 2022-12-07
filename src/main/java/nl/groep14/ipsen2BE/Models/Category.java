package nl.groep14.ipsen2BE.Models;

import javax.persistence.*;

/**
 * Represents the Category table in the database
 * @author Stijn van Beek
 */
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "customerID")
    private long id;
    private String name;
    private String voorwaarde;
    private boolean enabled;


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVoorwaarde() {
        return voorwaarde;
    }

    public void setVoorwaarde(String voorwaarde) {
        this.voorwaarde = voorwaarde;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", voorwaarde='" + voorwaarde + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
