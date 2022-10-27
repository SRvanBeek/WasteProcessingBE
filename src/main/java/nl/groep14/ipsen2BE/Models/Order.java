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
    private int customerID;

    @Column(name = "artikel_ID")
    private int artikelID;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "metrage")
    private float metrage;


    @Column(name = "address")
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getArtikelID() {
        return artikelID;
    }

    public void setArtikelID(int artikelID) {
        this.artikelID = artikelID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public float getMetrage() {
        return metrage;
    }

    public void setMetrage(float metrage) {
        this.metrage = metrage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerID=" + customerID +
                ", artikelID=" + artikelID +
                ", customerName='" + customerName + '\'' +
                ", metrage=" + metrage +
                ", address='" + address + '\'' +
                '}';
    }
}
