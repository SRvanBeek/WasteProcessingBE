package nl.groep14.ipsen2BE.Models;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "customer_ID")
    private long customerID;

    @Column(name = "artikel_ID")
    private long artikelID;

    @Column(name = "metrage")
    private float metrage;

    public Order(long customerID, long artikelID, float metrage) {
        this.customerID = customerID;
        this.artikelID = artikelID;
        this.metrage = metrage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public long getArtikelID() {
        return artikelID;
    }

    public void setArtikelID(int artikelID) {
        this.artikelID = artikelID;
    }

    public float getMetrage() {
        return metrage;
    }

    public void setMetrage(float metrage) {
        this.metrage = metrage;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerID=" + customerID +
                ", artikelID=" + artikelID +
                ", metrage=" + metrage +
                '}';
    }
}
