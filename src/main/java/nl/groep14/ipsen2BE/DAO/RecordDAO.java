package nl.groep14.ipsen2BE.DAO;

import org.springframework.stereotype.Component;
import nl.groep14.ipsen2BE.Models.Record;

@Component
public class RecordDAO {

    private final RecordRepository recordRepository;

    public RecordDAO(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public void removeFromDatabase(Record record) {
        this.recordRepository.delete(record);
    }

}
