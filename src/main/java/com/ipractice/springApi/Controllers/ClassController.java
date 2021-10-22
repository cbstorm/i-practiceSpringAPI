package com.ipractice.springApi.Controllers;


import com.ipractice.springApi.Entities.ClassEntity;
import com.ipractice.springApi.Exceptions.ResourceNotFoundException;
import com.ipractice.springApi.Schemas.ResponseSchema;
import com.ipractice.springApi.Services.ClassService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/class")
@RequiredArgsConstructor
public class ClassController {

    private ClassService classService;

    @PostMapping
    public ResponseEntity<ResponseSchema> createClass(@RequestHeader("userId") String id, @RequestBody ClassEntity classEntity){

        Map<String,Object> data = new HashMap<>();
        data.put("userId",id);
        data.put("classData",classEntity);

        ResponseSchema response = new ResponseSchema("success",data);
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.OK);
    }

}
