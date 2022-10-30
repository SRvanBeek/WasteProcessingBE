package nl.groep14.ipsen2BE.Controllers;

import nl.groep14.ipsen2BE.DAO.RecordDAO;
import nl.groep14.ipsen2BE.Models.ApiResponse;
import nl.groep14.ipsen2BE.Models.Record;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import nl.groep14.ipsen2BE.Exceptions.RecordNotFoundException;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/api/records")
public class RecordController {

    private final RecordDAO recordDAO;


    public RecordController(RecordDAO recordDAO) {
        this.recordDAO = recordDAO;
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse removeRecord(@RequestBody Record record){
        this.recordDAO.removeFromDatabase(record);
        return new ApiResponse(HttpStatus.ACCEPTED, "Record succesfully deleted from database.");
    }



    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> recordNotFound(
            RecordNotFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

}
