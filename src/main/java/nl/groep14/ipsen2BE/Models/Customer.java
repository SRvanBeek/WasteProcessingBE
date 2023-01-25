package nl.groep14.ipsen2BE.Models;

import javax.persistence.*;

/**
 * Represents the Customer table in the database
 * @author Stijn van Beek
 */
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customerID")
    private String customerID;
    @Column(name = "min_meter")
    private double min_meter;
    @Column(name = "max_meter")
    private double max_meter;

    @Column(name = "address")
    private String address;

    @Column (name = "country")
    private String country;

    @Column (name = "postal_code")
    private String postal_code;

    @Column (name = "city")
    private String city;

    @Column(name = "enabled")
    private boolean enabled;


    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postal_code;
    }

    public void setPostalCode(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", min_meter=" + min_meter +
                ", max_meter=" + max_meter +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", city='" + city + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
