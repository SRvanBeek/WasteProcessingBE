package nl.groep14.ipsen2BE.Models;

import org.springframework.http.HttpStatus;

import java.lang.reflect.Type;

public class ApiResponse<Type> {
    private HttpStatus code;
    private String message;


    public ApiResponse(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
