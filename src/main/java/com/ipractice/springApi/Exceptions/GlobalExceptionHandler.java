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

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseSchema> handleBadRequestException(BadRequestException exception){
        Map<String, String> resultResponse = new HashMap<>();
        resultResponse.put("errorMessage", exception.getMessage());
        ResponseSchema responseSchema = new ResponseSchema("failure",resultResponse);
        return new ResponseEntity(responseSchema, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseSchema> handleResourceNotFound(ResourceNotFoundException exception){
        Map<String, String> resultResponse = new HashMap<>();
        resultResponse.put("errorMessage", exception.getMessage());
        ResponseSchema responseSchema = new ResponseSchema("failure",resultResponse);
        return new ResponseEntity(responseSchema, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ResponseSchema> handleUnAuthorizedException(UnAuthorizedException exception){
        Map<String, String> resultResponse = new HashMap<>();
        resultResponse.put("errorMessage", exception.getMessage());
        ResponseSchema responseSchema = new ResponseSchema("failure",resultResponse);
        return new ResponseEntity(responseSchema, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseSchema> handleServerException(Exception exception) {
        Map<String, Object> resultResponse = new HashMap<>();
        resultResponse.put("errorMessage", exception.getMessage());
        resultResponse.put("cause",exception.getCause());
        ResponseSchema responseSchema = new ResponseSchema("failure",resultResponse);
        return new ResponseEntity(responseSchema, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
