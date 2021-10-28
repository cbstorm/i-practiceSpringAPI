package com.ipractice.springApi.Controllers;


import com.ipractice.springApi.Schemas.ResponseSchema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestConnectionController {

    @GetMapping
    public ResponseEntity<ResponseSchema> testConnection(){
        String data = "hello";
        ResponseSchema responseSchema = new ResponseSchema("success",data);
        return new ResponseEntity<ResponseSchema>(responseSchema, HttpStatus.OK);
    }

}
