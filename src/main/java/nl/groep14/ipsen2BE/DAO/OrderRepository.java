package nl.groep14.ipsen2BE.DAO;


import nl.groep14.ipsen2BE.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> getOrdersByLeftoverId(long id);
}
