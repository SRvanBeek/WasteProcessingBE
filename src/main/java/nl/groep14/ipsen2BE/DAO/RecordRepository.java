package nl.groep14.ipsen2BE.DAO;

import nl.groep14.ipsen2BE.Models.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record,Long> {
}
