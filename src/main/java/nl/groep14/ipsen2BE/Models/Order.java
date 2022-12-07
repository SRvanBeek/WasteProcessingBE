package nl.groep14.ipsen2BE.Models;

import javax.persistence.*;

/**
 * Order is the model of the Order entity.
 * @author Dino Yang
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "cutwasteID")
    private long cutwasteID;

    @Column(name = "userID")
    private long userID;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "date_processed")
    private String dateProcessed;

    public Order(){

    }

    public Order(long cutwasteID, long userID) {
        this.cutwasteID = cutwasteID;
        this.userID = userID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCutwasteID() {
        return cutwasteID;
    }

    public void setCutwasteID(long cutwasteID) {
        this.cutwasteID = cutwasteID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(String dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cutwasteID=" + cutwasteID +
                ", userID=" + userID +
                ", enabled=" + enabled +
                ", datecreated='" + dateProcessed + '\'' +
                '}';
    }
}
