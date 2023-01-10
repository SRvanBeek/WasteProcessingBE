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

    @Column(name = "leftoverID")
    private long leftoverId;

    @Column(name = "userID")
    private Integer userId;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "date_processed")
    private Date dateProcessed;

    public Voorraad(){

    }

    public Voorraad(long leftoverId, Integer userId, boolean enabled, Date dateProcessed) {
        this.leftoverId = leftoverId;
        this.userId = userId;
        this.enabled = enabled;
        this.dateProcessed = dateProcessed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getLeftoverId() {
        return leftoverId;
    }

    public void setLeftoverId(long leftoverId) {
        this.leftoverId = leftoverId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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
        return "Voorraad{" +
                "id=" + id +
                ", leftoverId=" + leftoverId +
                ", userId=" + userId +
                ", enabled=" + enabled +
                ", dateProcessed='" + dateProcessed + '\'' +
                '}';
    }
}
