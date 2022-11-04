package nl.groep14.ipsen2BE.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // 409
public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(long orderId){
        super("Order " + orderId + " does not exist.");
    }

}
