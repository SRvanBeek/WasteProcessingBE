package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.OrderDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import nl.groep14.ipsen2BE.Services.OrderService;



/**
 * OrderController is the controller for the Order Entity
 * @author Dino Yang
 */
@Controller
@RequestMapping(value = "/api/orders")
public class OrderController {

    private final OrderDAO orderDAO;
    private final OrderService orderService;


    public OrderController(OrderDAO orderDAO, OrderService orderService) {
        this.orderDAO = orderDAO;
        this.orderService = orderService;
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
    public ApiResponse getAllOrders(){
        return new ApiResponse(HttpStatus.ACCEPTED, this.orderDAO.getAll());
    }

    /**
     * getOneOrder returns one specific Order from the database.
     * @param id is the id of the Order
     * @return An Order
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getOneOrder(@PathVariable Long id){
        return new ApiResponse(HttpStatus.ACCEPTED, this.orderDAO.getOrderByID(id));
    }

    /**
     * getOneOrderByArticleId returns one Order based on the Article id of the order from the database.
     * @param leftoverID is the id of the cutWaste linked to the Order
     * @return An Order
     */
    @RequestMapping(value = "/perCutWaste/{leftoverID}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getOneOrderByLeftoverId(@PathVariable Long leftoverID){
        return new ApiResponse(HttpStatus.ACCEPTED, this.orderDAO.getOrdersByLeftoverId(leftoverID));
    }

    /**
     * disableOneOrder disables one Order from the database.
     * @param order order that needs to be disabled
     * @return ApiResponse with a corresponding message
     */
    @RequestMapping(value = "/disable", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse disableOneOrder(@RequestBody Order order){
        if (this.orderDAO.getOrderByID(order.getId()).isEmpty()){
            return new ApiResponse(HttpStatus.ACCEPTED, "Order "+order.getId()+" not found!");
        }else{
            this.orderDAO.setOrderVisibilityFalseByID(this.orderDAO.getOrderByID(order.getId()).get());
            return new ApiResponse(HttpStatus.ACCEPTED, "You disabled order "+order+"!");
        }
    }

    /**
     * putOrder puts an Order in the database.
     * @param order that needs to be put.
     * @return ApiRespone with response.
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse putOrder(@RequestBody Order order){
        this.orderDAO.saveToDatabase(order);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }

    @RequestMapping(value = "/artikel/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getArticleFromOrder(@PathVariable Long id){
        return new ApiResponse(HttpStatus.ACCEPTED, orderService.OrderToArticle(id));
    }
}
