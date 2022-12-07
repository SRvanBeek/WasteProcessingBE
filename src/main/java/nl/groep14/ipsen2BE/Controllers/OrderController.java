package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.OrderDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * OrderController is the controller for the Order Entity
 * @author Dino Yang
 */
@Controller
@RequestMapping(value = "/api/orders")
public class OrderController {

    private final OrderDAO orderDAO;


    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }


    /**
     * postOrder posts an Order into the database
     * @param order The OrderModel that is received in the PostMethod
     * @return ApiResponse with a corresponding message
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postOrder(@RequestBody Order order){
        this.orderDAO.saveToDatabase(order);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }
    /**
     * getAllOrders gets all orders from the database using the getAll method from the orderDAO.
     * The Orders are returned as a List.
     * @return a List with every Order in the database.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getAllOrders(){
        return this.orderDAO.getAll();
    }

    /**
     * getOneOrder returns one specific Order from the database.
     * @param id is the id of the Order
     * @return An Order
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Order getOneOrder(@PathVariable Long id){
        return this.orderDAO.getOrderByID(id).get();
    }

    /**
     * getOneOrderByArticleId returns one Order based on the Article id of the order from the database.
     * @param articleId is the id of the Article linked to the Order
     * @return An Order
     */
    @RequestMapping(value = "/perArticle/{articleId}", method = RequestMethod.GET)
    @ResponseBody
    public Order getOneOrderByArticleId(@PathVariable Long articleId){
        return this.orderDAO.getOrdersByCutWasteId(articleId).get();
    }

    /**
     * disableOneOrder disables one Order from the database.
     * @param order order that needs to be disabled
     * @return ApiResponse with a corresponding message
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse disableOneOrder(@RequestBody Order order){
        if (this.orderDAO.getOrderByID(order.getId()).isEmpty()){
            return new ApiResponse(HttpStatus.ACCEPTED, "Order "+order.getId()+" not found!");
        }else{
            this.orderDAO.setOrderVisibilityFalseByID(this.orderDAO.getOrderByID(order.getId()).get());
            return new ApiResponse(HttpStatus.ACCEPTED, "You disabled order "+order+"!");
        }
    }
}
