package nl.groep14.ipsen2BE.Models;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String voorwaarde;


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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id + '\'' +
                ", naam=" + name + '\'' +
                ", voorwaarde=" + voorwaarde +
                '}';
    }
}
