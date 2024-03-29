package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Order;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Optional;

/**
 * OrderDAO is the DAO of the Order entity
 * @author Dino Yang
 */
@Component
public class OrderDAO {

    private final OrderRepository orderRepository;

    public OrderDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    /**
     * Saves a single Order to the database.
     * @param order the Order that is to be saved
     */
    public void saveToDatabase(Order order){
        this.orderRepository.save(order);
    }
    /**
     * gets all Order in the database.
     * @return arrayList of Order
     */
    public ArrayList<Order> getAll(){
        return (ArrayList<Order>) this.orderRepository.findAll();
    }
    /**
     * Attempts to return a single Order if one exists in the database with the given id.
     * @param id The id that is used to find a specific Order.
     * @return An Order if an Order with the id exists.
     */
    public Optional<Order> getOrderByID(Long id){
        return this.orderRepository.findById(id);
    }
    /**
     * Attempts to return a single Order if one exists in the database with the given Article id.
     * @param id The Article id that is used to find a specific Order.
     * @return An Order if an Order with the Article id exists.
     */
    public Optional<Order> getOrdersByLeftoverId(Long id){
        return this.orderRepository.getOrdersByLeftoverId(id);
    }

    public void setOrderVisibilityFalseByID(Order order){
        order.setEnabled(false);
        this.orderRepository.save(order);
    }



}
