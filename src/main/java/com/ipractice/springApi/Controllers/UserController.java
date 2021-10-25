package com.ipractice.springApi.Controllers;


import com.ipractice.springApi.Entities.ClassEntity;
import com.ipractice.springApi.Schemas.ResponseSchema;
import com.ipractice.springApi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseSchema> getAllClassOfUser(@PathVariable("userId") String userId) {
        List<ClassEntity> data = userService.getAllClassOfUserId(userId);
        ResponseSchema response = new ResponseSchema("success", data);
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.OK);
    }

    @GetMapping("/invited/{userId}")
    public ResponseEntity<ResponseSchema> getAllInviteOfUser(@PathVariable("userId") String userId) {
        List<ClassEntity> data = userService.getAllInviteOfUserId(userId);
        ResponseSchema response = new ResponseSchema("success", data);
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.OK);
    }

    @PostMapping("/request/{classId}")
    public ResponseEntity<ResponseSchema> sendJoinRequest(@PathVariable("classId") UUID classId,@RequestHeader("userId") String userId){

        userService.sendJoinRequest(classId,userId);
        ResponseSchema response = new ResponseSchema();
        response.setStatus("success");
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.OK);
    }

    @PostMapping("/accept/{classId}")
    public ResponseEntity<ResponseSchema> acceptInvite(@PathVariable("classId") UUID classId, @RequestHeader("userId") String userId){

        userService.acceptInvite(classId,userId);
        ResponseSchema response = new ResponseSchema();
        response.setStatus("success");
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.OK);
    }

}
