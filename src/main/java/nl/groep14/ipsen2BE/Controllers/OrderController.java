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

@Controller
@RequestMapping(value = "/api/Order")
public class OrderController {

    private final OrderDAO orderDAO;
    private final OrderService orderService;


    public OrderController(OrderDAO orderDAO, OrderService orderService) {
        this.orderDAO = orderDAO;
        this.orderService = orderService;
    }



    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postOrder(@RequestBody Order order){
        //seervice
        this.orderDAO.saveToDatabase(order);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Order>> getAllOrders(){
        ArrayList<Order> orders = this.orderDAO.getAll();
        return new ApiResponse(HttpStatus.ACCEPTED, orders);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getOneOrder(@PathVariable Long id){
        return new ApiResponse(HttpStatus.ACCEPTED, this.orderDAO.getOrderByID(id));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchElementFoundException(
            OrderNotFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

}
