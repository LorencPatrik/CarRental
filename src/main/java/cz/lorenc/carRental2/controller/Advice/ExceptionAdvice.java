package cz.lorenc.carRental2.controller.Advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler
    public ResponseEntity<Object> handleExceptions(Exception e) {
        Map<String, String> errorResponseJson = new HashMap<>();
        errorResponseJson.put("error", "An error occurred");
        errorResponseJson.put("type", e.toString());
        return new ResponseEntity<>(errorResponseJson, HttpStatus.INTERNAL_SERVER_ERROR); // http status 500
    }

    @ExceptionHandler(EntityNotFoundException.class)  // here is official name of the exception which we expect
    public ResponseEntity<Object> handleEntityNotFound(Exception e) {
        Map<String, String> errorResponseJson = new HashMap<>();
        errorResponseJson.put("error", "EntityNotFoundException");
        errorResponseJson.put("type", e.toString());
        return new ResponseEntity<>(errorResponseJson, HttpStatus.NOT_FOUND); // http status 404
    }
}
