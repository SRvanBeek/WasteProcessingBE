package nl.groep14.ipsen2BE.Models;

import javax.persistence.*;
import java.util.Date;

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
    private Integer userID;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "date_processed")
    private Date dateProcessed;

    public Order(){

    }

    public Order(long cutwasteID, Integer userID, boolean enabled, Date dateProcessed) {
        this.cutwasteID = cutwasteID;
        this.userID = userID;
        this.enabled = enabled;
        this.dateProcessed = dateProcessed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCutwasteID() {
        return cutwasteID;
    }

    public void setCutwasteID(Long cutwasteID) {
        this.cutwasteID = cutwasteID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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
        return "Order{" +
                "id=" + id +
                ", cutwasteID=" + cutwasteID +
                ", userID=" + userID +
                ", enabled=" + enabled +
                ", datecreated='" + dateProcessed + '\'' +
                '}';
    }
}
