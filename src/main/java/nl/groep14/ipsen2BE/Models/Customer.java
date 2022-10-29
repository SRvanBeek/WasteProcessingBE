package nl.groep14.ipsen2BE.Models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "customer", schema = "vdl")
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_ID")
    private long customerId;
    @Basic
    @Column(name = "customer_name")
    private String customerName;
    @Basic
    @Column(name = "min_meter")
    private float minMeter;
    @Basic
    @Column(name = "max_meter")
    private float maxMeter;
    @Basic
    @Column(name = "address")
    private String address;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public float getMinMeter() {
        return minMeter;
    }

    public void setMinMeter(float minMeter) {
        this.minMeter = minMeter;
    }

    public float getMaxMeter() {
        return maxMeter;
    }

    public void setMaxMeter(float maxMeter) {
        this.maxMeter = maxMeter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", minMeter=" + minMeter +
                ", maxMeter=" + maxMeter +
                ", address='" + address + '\'' +
                '}';
    }
}
