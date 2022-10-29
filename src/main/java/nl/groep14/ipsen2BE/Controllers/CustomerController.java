package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.CustomerDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Article;
import nl.groep14.ipsen2BE.Models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "api/customer")
public class CustomerController {

    private final CustomerDAO customerDAO;


    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postCustomer(@RequestBody Customer customer){
        this.customerDAO.saveToDatabase(customer);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getAllCustomers(){
        List<Customer> customers = this.customerDAO.getAll();
        return customers;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Customer getOneCustomer(@PathVariable Long id){
        return this.customerDAO.getCustomerByID(id).get();
    }




}
