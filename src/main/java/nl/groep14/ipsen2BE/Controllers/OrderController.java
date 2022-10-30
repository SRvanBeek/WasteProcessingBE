package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.OrderDAO;
import nl.groep14.ipsen2BE.Exceptions.OrderNotFoundException;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Order;
import nl.groep14.ipsen2BE.Services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/orders")
public class OrderController {

    private final OrderDAO orderDAO;


    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }



    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postOrder(@RequestBody Order order){
        this.orderDAO.saveToDatabase(order);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getAllOrders(){
        return this.orderDAO.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Order getOneOrder(@PathVariable Long id){
        return this.orderDAO.getOrderByID(id).get();
    }

    @RequestMapping(value = "/highestIDOrder", method = RequestMethod.GET)
    @ResponseBody
    public Order getHighestIdOrder(){
        return this.orderDAO.getMaxIdOrder();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse putOneOrder(@PathVariable Long id,@RequestBody Order newOrder){
        Order currentOrder = orderDAO.getOrderByID(id).get();
        Long currentID = currentOrder.getId();
        currentOrder = newOrder;
        currentOrder.setId(currentID);
        this.orderDAO.saveToDatabase(currentOrder);
        return new ApiResponse(HttpStatus.ACCEPTED, "You updated order"+currentID+"!");
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> orderNotFound(
            OrderNotFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

}
