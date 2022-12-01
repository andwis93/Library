package com.library.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotExistsException.class)
    public ResponseEntity<Object> handleReaderNotFoundException(RecordNotExistsException exception) {
        return new ResponseEntity<>("Object with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<Object> handleReaderEmptyFieldException(EmptyFieldException exception) {
        return new ResponseEntity<>("One of the required Fields is Empty", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CopyNotAvailableException.class)
    public ResponseEntity<Object> handleCopyNotAvailableException(CopyNotAvailableException exception) {
        return new ResponseEntity<>("Copy not available", HttpStatus.LOCKED);
    }
}
