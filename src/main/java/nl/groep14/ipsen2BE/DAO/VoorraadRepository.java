package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Voorraad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoorraadRepository extends JpaRepository<Voorraad,Long> {
}
