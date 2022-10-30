package nl.groep14.ipsen2BE.DAO;


import nl.groep14.ipsen2BE.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrderRepository extends JpaRepository<Order,Long> {

}
