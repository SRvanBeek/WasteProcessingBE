package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
@Component
public class OrderDAO {

    private final OrderRepository orderRepository;

    public OrderDAO(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveToDatabase(Order order){
        this.orderRepository.save(order);
    }

    public ArrayList<Order> getAll(){
        return (ArrayList<Order>) this.orderRepository.findAll();
    }

    public Optional<Order> getOrderByID(Long id){
        return this.orderRepository.findById(id);
    }

    public Order getMaxIdOrder(){
        return this.orderRepository.getMaxID();
    }

}
