package nl.groep14.ipsen2BE.DAO;


import nl.groep14.ipsen2BE.Models.Order;
import nl.groep14.ipsen2BE.Models.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order,Long> {


//    @Query(value = "SELECT o from Order o where o.artikelID = :artikelID")
//    Optional<Order> getOrdersByArtikelID(@Param("artikelID") long artikelId);
}
