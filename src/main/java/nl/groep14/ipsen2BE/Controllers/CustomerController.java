package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.CustomerDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


/**
 * Spring Controller for handling Customer-related Requests
 * @author Stijn van Beek
 */
@Controller
@RequestMapping(value = "/api/customers")
public class CustomerController {
    private final CustomerDAO customerDAO;

    /**
     * Class Constructer, initializes the CustomerDAO.
     * @param customerDAO The CustomerDAO that will be initialized in this Controller.
     */
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
    /**
     * Checks if the given Customer's ID exists in the database. When it does exist the given Customer is updated
     * into the database using the saveToDatabase method in the CustomerDAO.
     *
     * @param customer The CustomerModel that is received in the PUT-Request body.
     * @return ApiResponse with a corresponding message.
     *
     * @see CustomerDAO#saveToDatabase(Customer)
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse<String> updateCustomer(@RequestBody Customer customer) {
        if(this.customerDAO.getCustomerByID(customer.getCustomerID()).isEmpty()){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "customer does not exist!");
        }else {
            this.customerDAO.saveToDatabase(customer);
            return new ApiResponse<>(HttpStatus.ACCEPTED, "customer updated");
        }
    }

    /**
     * Adds the given Customer to the database using the saveToDataBase method in the CustomerDAO.
     *
     * @param customer The CustomerModel that is received in the POST-Request body.
     * @return ApiResponse with a corresponding message.
     *
     * @see CustomerDAO#saveToDatabase(Customer)
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<String> addCustomer(@RequestBody Customer customer) {
        this.customerDAO.saveToDatabase(customer);
        return new ApiResponse<>(HttpStatus.ACCEPTED, "You've posted Customer with id " + customer.getCustomerID());
    }

    /**
     * Gets all Customers from the database using the getAll method from the CustomerDAO.
     * The customers are returned as an ArrayList.
     *
     * @return An ApiResponse containing an ArrayList with every Customer in the database.
     * @see CustomerDAO#getAll()
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Customer>> customers(){
        ArrayList<Customer> customers = this.customerDAO.getAll();
        return new ApiResponse<>(HttpStatus.ACCEPTED, customers);
    }

    /**
     * nameExist checks whether the customer name exists or not.
     * @param id of the customer
     * @return ApiResponse with a string containing 'exist' or Doesn't exist
     */
    @RequestMapping(value = "/exist/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<String> nameExist(@PathVariable String id){
        if (this.customerDAO.customerExists(id)){
            return new ApiResponse<>(HttpStatus.ACCEPTED,"exist");
        }else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND,"Doesn't exist");
        }
    }

    /**
     * Attempts to return a single Customer that contains the given ID from the database using the getCustomerByID
     * method from the CategoryDAO.
     *
     * @param id The id acquired from the @RequestMapping annotation.
     * @return ApiResponse with a corresponding message.
     * @see CustomerDAO#getCustomerByID(String)
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<Customer> getCustomerByID(@PathVariable String id){
        if (this.customerDAO.getCustomerByID(id).isEmpty()){
            return new ApiResponse<>(HttpStatus.NOT_FOUND, "No customer found with id " + id);
        }else{
            return new ApiResponse<>(HttpStatus.ACCEPTED, this.customerDAO.getCustomerByID(id).get());
        }
    }
}
