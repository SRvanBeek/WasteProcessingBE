package nl.groep14.ipsen2BE.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // 409
public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException(long recordID){
        super("Record " + recordID + " does not exist.");
    }

}
