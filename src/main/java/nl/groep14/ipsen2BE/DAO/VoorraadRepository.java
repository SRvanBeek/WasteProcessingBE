package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Voorraad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoorraadRepository extends JpaRepository<Voorraad,Long> {
    Optional<Voorraad> findVoorraadByLeftoverId(Long id);
}
