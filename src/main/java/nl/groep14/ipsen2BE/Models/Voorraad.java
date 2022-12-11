package nl.groep14.ipsen2BE.Models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "voorraad")
public class Voorraad {

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

    public Voorraad(){

    }

    public Voorraad(long cutwasteID, Integer userID, boolean enabled, Date dateProcessed) {
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

    public long getCutwasteID() {
        return cutwasteID;
    }

    public void setCutwasteID(long cutwasteID) {
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
        return "Voorraad{" +
                "id=" + id +
                ", cutwasteID=" + cutwasteID +
                ", userID=" + userID +
                ", enabled=" + enabled +
                ", dateProcessed='" + dateProcessed + '\'' +
                '}';
    }
}
