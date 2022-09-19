package learn.field_agent.controllers;

import learn.field_agent.data.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataException.class)
    public ResponseEntity handleDataException( DataException toHandle) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(toHandle.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException( Exception toHande ){
        return ResponseEntity.internalServerError().build();
    }
}

