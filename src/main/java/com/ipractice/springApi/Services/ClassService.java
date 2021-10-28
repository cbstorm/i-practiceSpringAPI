package com.ipractice.springApi.Services;

import com.ipractice.springApi.Entities.ClassEntity;
import com.ipractice.springApi.Entities.InvitedEntity;
import com.ipractice.springApi.Entities.JoinRequestEntity;
import com.ipractice.springApi.Entities.JoinedEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ClassService {

    ClassEntity createClass(String userId, ClassEntity classEntity);

    List<ClassEntity> getAllClass();

    ClassEntity getOneClass(UUID classId);

    List<JoinedEntity> getAllUserOfClass(UUID classId);

    void inviteMembers(UUID classId, String adminId, List<String> listUserId);

    List<InvitedEntity> getAllUserInvitedOfClass(UUID classId);

    List<JoinRequestEntity> getAllJoinRequest(UUID classId);

    void acceptJoinRequest(UUID classId, String adminId, UUID requestId);

    void rejectJoinRequest(UUID classId, String adminId, UUID requestId);

    void deleteMember(UUID classId, String adminId, UUID joinedId);

    int getMemberAmount(UUID classId);


}
