package nl.groep14.ipsen2BE.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryOutOfBoundsException extends RuntimeException {
    public CategoryOutOfBoundsException(){
            super("Category values must be between 0 and 100!");
        }
}
