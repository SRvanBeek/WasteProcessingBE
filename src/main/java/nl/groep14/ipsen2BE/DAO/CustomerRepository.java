package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
