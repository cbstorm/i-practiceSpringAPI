package com.ipractice.springApi.Services;


import com.ipractice.springApi.Entities.ClassEntity;
import com.ipractice.springApi.Entities.JoinedEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<ClassEntity> getAllClassOfUserId(String userId);

    List<ClassEntity> getAllInviteOfUserId(String userId);

    void sendJoinRequest(UUID classId, String userId);
    void acceptInvite(UUID classId, String userId);


}
