package com.ipractice.springApi.ServicesImpl;

import com.ipractice.springApi.Entities.ClassEntity;
import com.ipractice.springApi.Entities.InvitedEntity;
import com.ipractice.springApi.Entities.JoinRequestEntity;
import com.ipractice.springApi.Entities.JoinedEntity;
import com.ipractice.springApi.Exceptions.ResourceNotFoundException;
import com.ipractice.springApi.Repositories.ClassRepository;
import com.ipractice.springApi.Repositories.InvitedRepository;
import com.ipractice.springApi.Repositories.JoinRequestRepository;
import com.ipractice.springApi.Repositories.JoinedRepository;
import com.ipractice.springApi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JoinedRepository joinedRepository;

    @Autowired
    private InvitedRepository invitedRepository;

    @Autowired
    private ClassRepository classRepository;


    @Override
    public List<ClassEntity> getAllClassOfUserId(String userId) {

        List<JoinedEntity> listJoined = joinedRepository.findAllByUserId(userId);
        List<ClassEntity> listJoinedClasses = listJoined.stream().map((joinedItem)->{
            ClassEntity classEntity = joinedItem.getClassEntity();
            return classEntity;
        }).collect(Collectors.toList());
        return listJoinedClasses;
    }

    @Override
    public List<ClassEntity> getAllInviteOfUserId(String userId) {

        List<InvitedEntity> listInvited = invitedRepository.findAllByUserId(userId);
        List<ClassEntity> listJoinedClasses = listInvited.stream().map((invitedItem)->{
            ClassEntity classEntity = invitedItem.getClassEntity();
            return classEntity;
        }).collect(Collectors.toList());
        return listJoinedClasses;
    }

    @Override
    public void sendJoinRequest(UUID classId, String userId) {
        ClassEntity classEntity = classRepository.findById(classId).orElseThrow(()-> new ResourceNotFoundException("NOT_FOUND"));
        JoinRequestEntity joinRequest = new JoinRequestEntity();
        joinRequest.setUserId(userId);
        joinRequest.setClassEntity(classEntity);
        List<JoinRequestEntity> listJoinRequest = classEntity.getJoinRequest();
        listJoinRequest.add(joinRequest);
        classEntity.setJoinRequest(listJoinRequest);
        classRepository.save(classEntity);
    }

    @Override
    public void acceptInvite(UUID classId, String userId) {
        InvitedEntity invited = invitedRepository.findOneByClassIdAndUserId(classId,userId).orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND"));
        ClassEntity classEntity = invited.getClassEntity();
        JoinedEntity joinedEntity = new JoinedEntity();
        joinedEntity.setUserId(userId);
        joinedEntity.setClassEntity(classEntity);
        joinedEntity.setRole("member");
        List<JoinedEntity> joinedList = classEntity.getJoined();
        joinedList.add(joinedEntity);
        classEntity.setJoined(joinedList);
        invitedRepository.delete(invited);
        classRepository.save(classEntity);

    }
}
