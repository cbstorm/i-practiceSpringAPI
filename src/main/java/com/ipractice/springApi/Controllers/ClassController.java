package com.ipractice.springApi.Controllers;


import com.ipractice.springApi.Entities.ClassEntity;
import com.ipractice.springApi.Entities.InvitedEntity;
import com.ipractice.springApi.Entities.JoinRequestEntity;
import com.ipractice.springApi.Entities.JoinedEntity;
import com.ipractice.springApi.Schemas.ResponseSchema;
import com.ipractice.springApi.Services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/class")
public class ClassController {

    @Autowired
    private ClassService classService;


    // CREATE CLASS
    @PostMapping
    public ResponseEntity<ResponseSchema> createClass(@RequestHeader("userId") String userId, @RequestBody ClassEntity classEntity) {

        ClassEntity classCreated = classService.createClass(userId, classEntity);
        ResponseSchema response = new ResponseSchema("success", classCreated);
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.CREATED);
    }

    // GET ALL classes
    @GetMapping
    public ResponseEntity<ResponseSchema> getAllClass() {
        List<ClassEntity> listClasses = classService.getAllClass();
        ResponseSchema response = new ResponseSchema("success", listClasses);
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.OK);
    }

    // Invite members
    @PostMapping("/members/{classId}")
    public ResponseEntity<ResponseSchema> inviteMembers(@PathVariable("classId") UUID classId, @RequestHeader("userId") String adminId, @RequestHeader("userId") String userId, @RequestBody Map<String, List<String>> body) {
        List<String> listUserId = body.get("members");
        classService.inviteMembers(classId, adminId, listUserId);
        ResponseSchema response = new ResponseSchema();
        response.setStatus("success");
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.OK);
    }

    // GET ALL USERS
    @GetMapping("/members/{classId}")
    public ResponseEntity<ResponseSchema> getAllUserOfClass(@PathVariable("classId") UUID classId) {
        List<JoinedEntity> listUserJoined = classService.getAllUserOfClass(classId);
        ResponseSchema response = new ResponseSchema("success", listUserJoined);
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.OK);
    }

    // GET ALL invited
    @GetMapping("/members/invited/{classId}")
    public ResponseEntity<ResponseSchema> getAllInvitedOfClass(@PathVariable("classId") UUID classId) {
        List<InvitedEntity> listUserInvite = classService.getAllUserInvitedOfClass(classId);
        ResponseSchema response = new ResponseSchema("success", listUserInvite);
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.OK);
    }

    // GET ALl Join Requests
    @GetMapping("/request/{classId}")
    public ResponseEntity<ResponseSchema> getAllJoinRequestOfClass(@PathVariable("classId") UUID classId){
        List<JoinRequestEntity> listJoinRequest = classService.getAllJoinRequest(classId);
        ResponseSchema response = new ResponseSchema("success", listJoinRequest);
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.OK);
    }

    @PutMapping("/request/accept/{classId}")
    public ResponseEntity<ResponseSchema> acceptJoinRequestOfClass(@PathVariable("classId") UUID classId,@RequestHeader("userId") String adminId ,@RequestBody Map<String,UUID> body){
        UUID requestId = body.get("requestId");
        classService.acceptJoinRequest(classId,adminId,requestId);
        ResponseSchema response = new ResponseSchema();
        response.setStatus("success");
        return new ResponseEntity<ResponseSchema>(response, HttpStatus.OK);
    }


}
