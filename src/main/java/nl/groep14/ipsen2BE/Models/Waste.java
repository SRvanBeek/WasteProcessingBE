package nl.groep14.ipsen2BE.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Waste is the model of the Waste entity.
 * @author Dino Yang
 */
@Entity
@Table(name = "afval")
public class Waste {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "cutwasteID")
    private long cutwasteId;

    @Basic
    @Column(name = "categoryID")
    private long categoryId;

    @Basic
    @Column(name = "userID")
    private long userId;

    @Basic
    @Column(name = "enabled")
    private boolean enabled;

    @Basic
    @Column(name = "date_processed")
    private Date dateProcessed;

    public Waste(long cutwasteId, long categoryId, long userId) {
        this.cutwasteId = cutwasteId;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public Waste() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCutwasteId() {
        return cutwasteId;
    }

    public void setCutwasteId(long cutwasteId) {
        this.cutwasteId = cutwasteId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(Date dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    @Override
    public String toString() {
        return "Waste{" +
                "id=" + id +
                ", cutwasteId=" + cutwasteId +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                ", enabled=" + enabled +
                ", dateCreated='" + dateProcessed + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Waste waste = (Waste) o;
        return id == waste.id && cutwasteId == waste.cutwasteId && categoryId == waste.categoryId && userId == waste.userId && enabled == waste.enabled && Objects.equals(dateProcessed, waste.dateProcessed);
    }

}
