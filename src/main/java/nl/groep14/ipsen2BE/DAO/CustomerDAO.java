package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Data Access Object for managing Customers in the database.
 * @author Stijn van Beek
 */
@Component
public class CustomerDAO {
    private final CustomerRepository customerRepository;

    /**
     * Class Constructor, initializes the CustomerRepository
     * @param customerRepository The CustomerRepository to be initialized.
     */
    public CustomerDAO(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Adds the given Customer to the database, or updates a Customer when this method is called with a PUT-Request
     * @param customer The Customer that gets added or updated in the database.
     */
    public void saveToDatabase(Customer customer){
        this.customerRepository.save(customer);
    }

    /**
     * Gets every Customer from the database and returns it as an ArrayList
     *
     * @return An ArrayList with every Customer in the database
     */
    public ArrayList<Customer> getAll(){
        return (ArrayList<Customer>) this.customerRepository.findAll();
    }

    /**
     * Attempts to return a single Customer if one exists in the database with the given id.
     *
     * @param id The id that is used to find a specific Customer.
     * @return A Customer if a Customer with the id exists.
     */
    public Optional<Customer> getCustomerByID(String id){
        return this.customerRepository.getCustomerByCustomerName(id);
    }
}

