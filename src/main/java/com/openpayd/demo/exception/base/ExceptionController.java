package com.openpayd.demo.exception.base;

import com.openpayd.demo.exception.RatePairNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//not used because of flexiblity
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = RatePairNotFound.class)
    public ResponseEntity<Object> exception(RatePairNotFound exception) {
        return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> exception(IllegalArgumentException exception) {
        return new ResponseEntity<>("Illegal time not found", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Object> exception(RuntimeException exception) {
        return new ResponseEntity<>("Run time not found", HttpStatus.INTERNAL_SERVER_ERROR);

    }
}