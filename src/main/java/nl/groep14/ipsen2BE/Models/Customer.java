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
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "customerID")
    private String customerID;
    @Column(name = "min_meter")
    private double min_meter;
    @Column(name = "max_meter")
    private double max_meter;

    @Column(name = "address")
    private String address;

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
                ", enabled=" + enabled +
                '}';
    }
}
