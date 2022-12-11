package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Customer;
import nl.groep14.ipsen2BE.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
//    @Query(value = "SELECT c from Customer c where c.customerID = :customerName")
//    Optional<Customer> getCustomerByCustomerName(@Param("customerName") String customerName);

    Optional<Customer> getCustomersByCustomerID(String id);
}
