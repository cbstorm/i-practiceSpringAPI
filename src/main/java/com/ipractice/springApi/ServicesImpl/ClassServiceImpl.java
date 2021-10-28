package com.ipractice.springApi.ServicesImpl;

import com.ipractice.springApi.Entities.ClassEntity;
import com.ipractice.springApi.Entities.InvitedEntity;
import com.ipractice.springApi.Entities.JoinRequestEntity;
import com.ipractice.springApi.Entities.JoinedEntity;
import com.ipractice.springApi.Exceptions.ResourceNotFoundException;
import com.ipractice.springApi.Exceptions.UnAuthorizedException;
import com.ipractice.springApi.Repositories.ClassRepository;
import com.ipractice.springApi.Repositories.JoinRequestRepository;
import com.ipractice.springApi.Repositories.JoinedRepository;
import com.ipractice.springApi.Services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private JoinedRepository joinedRepository;

    @Autowired
    private JoinRequestRepository joinRequestRepository;


    @Override
    public ClassEntity createClass(String userId, ClassEntity classEntity) {
        // SET Joined
        JoinedEntity joined = new JoinedEntity();
        joined.setUserId(userId);
        joined.setClassEntity(classEntity);
        joined.setRole("admin");

        List<JoinedEntity> joinedList = new ArrayList<>();
        joinedList.add(joined);

        classEntity.setAdminUser(userId);
        classEntity.setJoined(joinedList);
        //Save class
        ClassEntity savedClass = classRepository.save(classEntity);
        return savedClass;

    }

    @Override
    public List<ClassEntity> getAllClass() {
        return classRepository.findAll();
    }

    @Override
    public ClassEntity getOneClass(UUID classId) {
        ClassEntity classEntity = classRepository.findById(classId).orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND"));
        return classEntity;
    }

    @Override
    public List<JoinedEntity> getAllUserOfClass(UUID classId) {
        ClassEntity classEntity = classRepository.findById(classId).orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND"));
        List<JoinedEntity> listJoined = classEntity.getJoined();
        return listJoined;
    }

    @Override
    public int getMemberAmount(UUID classId) {
        int memberAmount = joinedRepository.countJoinedByClassId(classId);
        return memberAmount;
    }

    @Override
    public List<InvitedEntity> getAllUserInvitedOfClass(UUID classId) {
        ClassEntity classEntity = classRepository.findById(classId).orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND"));
        List<InvitedEntity> listInvited = classEntity.getInvited();
        return listInvited;
    }

    @Override
    public List<JoinRequestEntity> getAllJoinRequest(UUID classId) {
        ClassEntity classEntity = classRepository.findById(classId).orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND"));
        List<JoinRequestEntity> listJoinRequest = classEntity.getJoinRequest();
        return listJoinRequest;
    }

    @Override
    public void deleteMember(UUID classId, String adminId, UUID joinedId) {
        ClassEntity classEntity = classRepository.findById(classId).orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND"));
        if (!classEntity.getAdminUser().equals(adminId)) {
            throw new UnAuthorizedException("UNAUTHORIZED");
        }
        JoinedEntity joined = joinedRepository.findById(joinedId).orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND"));
        joinedRepository.delete(joined);
    }

    @Override
    public void acceptJoinRequest(UUID classId, String adminId, UUID requestId) {
        ClassEntity classEntity = classRepository.findById(classId).orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND"));
        if (!classEntity.getAdminUser().equals(adminId)) {
            throw new UnAuthorizedException("UNAUTHORIZED");
        }
        JoinRequestEntity joinRequest = joinRequestRepository.findById(requestId).orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND"));
        JoinedEntity joined = new JoinedEntity();
        joined.setUserId(joinRequest.getUserId());
        joined.setRole("member");
        joined.setClassEntity(classEntity);
        List<JoinedEntity> listJoined = classEntity.getJoined();
        listJoined.add(joined);
        classEntity.setJoined(listJoined);
        joinRequestRepository.delete(joinRequest);
        classRepository.save(classEntity);
    }

    @Override
    public void rejectJoinRequest(UUID classId, String adminId, UUID requestId) {
        ClassEntity classEntity = classRepository.findById(classId).orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND"));
        if (!classEntity.getAdminUser().equals(adminId)) {
            throw new UnAuthorizedException("UNAUTHORIZED");
        }
        JoinRequestEntity joinRequest = joinRequestRepository.findById(requestId).orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND"));
        joinRequestRepository.delete(joinRequest);
    }


    @Override
    public void inviteMembers(UUID classId, String adminId, List<String> listUserId) {
        ClassEntity classEntity = classRepository.findById(classId).orElseThrow(() -> new ResourceNotFoundException("NOT_FOUND"));
        if (!classEntity.getAdminUser().equals(adminId)) {
            throw new UnAuthorizedException("UNAUTHORIZED");
        }
        List<InvitedEntity> invitedMemberList = new ArrayList<>();
        for (String id : listUserId) {
            InvitedEntity invitedMember = new InvitedEntity();
            invitedMember.setUserId(id);
            invitedMember.setClassEntity(classEntity);
            invitedMemberList.add(invitedMember);
        }
        List<InvitedEntity> invitedEntityList = classEntity.getInvited();
        invitedEntityList.addAll(invitedMemberList);
        classEntity.setInvited(invitedEntityList);
        classRepository.save(classEntity);
    }


}
