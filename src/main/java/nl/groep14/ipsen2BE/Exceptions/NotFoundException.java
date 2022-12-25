package nl.groep14.ipsen2BE.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 409
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }

}
