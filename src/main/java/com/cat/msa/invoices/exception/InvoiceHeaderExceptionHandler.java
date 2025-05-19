package com.cat.msa.invoices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvoiceHeaderExceptionHandler {

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<String> handleDuplicationException(DuplicateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Void> handleNoContentException(NoContentException ex) {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleInvoiceNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
