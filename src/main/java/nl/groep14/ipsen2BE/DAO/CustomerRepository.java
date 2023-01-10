package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> getCustomersByCustomerID(String id);
}
