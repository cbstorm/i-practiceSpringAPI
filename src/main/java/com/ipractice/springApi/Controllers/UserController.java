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

    @PutMapping("/request-destroy/{classId}")
    public ResponseEntity<ResponseSchema> sendOrDestroyJoinRequest(@PathVariable("classId") UUID classId,@RequestHeader("userId") String userId){

        userService.sendOrDestroyJoinRequest(classId,userId);
        ResponseSchema response = new ResponseSchema();
        response.setStatus("success");
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.OK);
    }

    @PutMapping("/accept/{classId}")
    public ResponseEntity<ResponseSchema> acceptInvite(@PathVariable("classId") UUID classId, @RequestHeader("userId") String userId){

        userService.acceptInvite(classId,userId);
        ResponseSchema response = new ResponseSchema();
        response.setStatus("success");
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.OK);
    }

    @DeleteMapping("/reject/{classId}")
    public ResponseEntity<ResponseSchema> rejectInvite(@PathVariable("classId") UUID classId, @RequestHeader("userId") String userId){

        userService.rejectInvite(classId,userId);
        ResponseSchema response = new ResponseSchema();
        response.setStatus("success");
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.OK);
    }

    @DeleteMapping("/leave/{classId}")
    public ResponseEntity<ResponseSchema> leaveClass(@PathVariable("classId") UUID classId, @RequestHeader("userId") String userId){

        userService.leaveClass(classId,userId);
        ResponseSchema response = new ResponseSchema();
        response.setStatus("success");
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.OK);
    }

}
