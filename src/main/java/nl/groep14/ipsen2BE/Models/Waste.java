package nl.groep14.ipsen2BE.Models;

import javax.persistence.*;
import java.util.Objects;

/**
 * Waste is the model of the Waste entity.
 * @author Dino Yang
 */
@Entity
@Table(name = "afval", schema = "vdl")
public class Waste {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "afval_ID")
    private long afvalId;
    @Basic
    @Column(name = "artikel_ID")
    private long artikelId;
    @Basic
    @Column(name = "metrage")
    private long metrage;
    @Basic
    @Column(name = "categories")
    private String categories;

    public Waste(long artikelId, long metrage, String categories) {
        this.artikelId = artikelId;
        this.metrage = metrage;
        this.categories = categories;
    }

    public Waste() {

    }

    public long getAfvalId() {
        return afvalId;
    }

    public void setAfvalId(long afvalId) {
        this.afvalId = afvalId;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(long artikelId) {
        this.artikelId = artikelId;
    }

    public long getMetrage() {
        return metrage;
    }

    public void setMetrage(long metrage) {
        this.metrage = metrage;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Waste{" +
                "afvalId=" + afvalId +
                ", artikelId=" + artikelId +
                ", metrage=" + metrage +
                ", categories='" + categories + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Waste waste = (Waste) o;
        return afvalId == waste.afvalId && artikelId == waste.artikelId && metrage == waste.metrage && Objects.equals(categories, waste.categories);
    }
}
