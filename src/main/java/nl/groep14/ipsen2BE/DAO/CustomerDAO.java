package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class CustomerDAO {

    private final CustomerRepository customerRepository;

    public CustomerDAO(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public void saveToDatabase(Customer customer){
        this.customerRepository.save(customer);
    }

    public ArrayList<Customer> getAll(){
        return (ArrayList<Customer>) this.customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByID(Long id){
        return this.customerRepository.findById(id);
    }
}

