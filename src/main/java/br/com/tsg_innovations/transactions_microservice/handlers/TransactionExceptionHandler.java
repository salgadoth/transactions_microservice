package br.com.tsg_innovations.transactions_microservice.handlers;

import br.com.tsg_innovations.transactions_microservice.exceptions.InvalidTransactionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransactionExceptionHandler {
    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<Object> handle(InvalidTransactionException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
