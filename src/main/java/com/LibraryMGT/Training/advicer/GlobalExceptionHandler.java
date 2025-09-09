package com.LibraryMGT.Training.advicer;

import com.LibraryMGT.Training.exceptions.EntryDuplicateException;
import com.LibraryMGT.Training.exceptions.NotFoundException;
import com.LibraryMGT.Training.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> NotFoundExceptionHandler(NotFoundException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404, "Nothing found", e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(EntryDuplicateException.class)
    public ResponseEntity<StandardResponse> DuplcationEntryHandler(EntryDuplicateException e) {
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(409, "Duplication Error", e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}