package com.ipractice.springApi.Exceptions;

import com.ipractice.springApi.Schemas.ResponseSchema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseSchema> handleResourceNotFound(ResourceNotFoundException exception){
        Map<String, String> resultResponse = new HashMap<>();
        resultResponse.put("errorMessage", exception.getMessage());
        ResponseSchema responseSchema = new ResponseSchema("failure",resultResponse);
        return new ResponseEntity(responseSchema, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseSchema> handleServerException() {
        Exception error = new Exception("SERVER_ERROR");
        Map<String, String> resultResponse = new HashMap<>();
        resultResponse.put("errorMessage", error.getMessage());
        ResponseSchema responseSchema = new ResponseSchema("failure",resultResponse);
        return new ResponseEntity(responseSchema, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
