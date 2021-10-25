package com.ipractice.springApi.Repositories;

import com.ipractice.springApi.Entities.InvitedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InvitedRepository extends JpaRepository<InvitedEntity, UUID> {
    @Query("SELECT i FROM invited i WHERE i.userId = :userid")
    List<InvitedEntity> findAllByUserId(@Param("userid") String userId);

    @Query(value = "SELECT * FROM invited WHERE class_id = :classId AND user_id = :userid",nativeQuery = true)
    Optional<InvitedEntity> findOneByClassIdAndUserId(@Param("classId") UUID classId,@Param("userid") String userId);
}
