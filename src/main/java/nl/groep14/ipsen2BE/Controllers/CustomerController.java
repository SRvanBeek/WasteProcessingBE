package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.CategoryDAO;
import nl.groep14.ipsen2BE.DAO.CustomerDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Category;
import nl.groep14.ipsen2BE.Models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/customers")
public class CustomerController {
    private final CustomerDAO customerDAO;

    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse updateCustomer(@RequestBody Customer customer) {
        if(this.customerDAO.getCustomerByID(customer.getId()).isEmpty()){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "customer does not exist!");
        }

        this.customerDAO.saveToDatabase(customer);
        return new ApiResponse<>(HttpStatus.ACCEPTED, "customer updated");
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Customer addCustomer(@RequestBody Customer customer) {

        this.customerDAO.saveToDatabase(customer);
        return customer;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Customer> customers(){
        ArrayList<Customer> customers = this.customerDAO.getAll();
        return customers;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Optional<Customer> getCustomerByID(@PathVariable long id){
        Optional<Customer> customer = this.customerDAO.getCustomerByID(id);
        return customer;
    }
}
