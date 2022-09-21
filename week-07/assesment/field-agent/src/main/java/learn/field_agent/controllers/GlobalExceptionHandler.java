package learn.field_agent.controllers;

import learn.field_agent.data.DataException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataException.class)
    public ResponseEntity handleDataException( DataException toHandle) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(toHandle.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handelHttpMessageNotReadable(HttpMessageNotReadableException ex){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> dataIntegrityViolationException(DataIntegrityViolationException ex){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }





//    @ExceptionHandler(Exception.class)
//    public ResponseEntity handleGenericException( Exception toHande ){
//        return ResponseEntity.internalServerError().build();
//    }
}

