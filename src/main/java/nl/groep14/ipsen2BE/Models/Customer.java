package nl.groep14.ipsen2BE.Models;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String name;
    private double min_meter;
    private double max_meter;
    private String address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMin_meter() {
        return min_meter;
    }

    public void setMin_meter(double min_meter) {
        this.min_meter = min_meter;
    }

    public double getMax_meter() {
        return max_meter;
    }

    public void setMax_meter(double max_meter) {
        this.max_meter = max_meter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id + '\'' +
                ", name=" + name + '\'' +
                ", min_meter=" + min_meter + '\'' +
                ", max_meter=" + max_meter + '\'' +
                ", address=" + address +
                '}';
    }
}
