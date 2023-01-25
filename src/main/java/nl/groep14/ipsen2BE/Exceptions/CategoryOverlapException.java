package nl.groep14.ipsen2BE.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryOverlapException extends RuntimeException {
    public CategoryOverlapException(String categoryName){
            super("Category overlaps with category " + categoryName +"!");
        }
}
