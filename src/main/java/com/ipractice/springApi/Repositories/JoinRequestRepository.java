package com.ipractice.springApi.Repositories;

import com.ipractice.springApi.Entities.JoinRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JoinRequestRepository extends JpaRepository<JoinRequestEntity, UUID> {

    @Query(value = "SELECT * FROM join_requests WHERE id = :id AND class_id = :classId",nativeQuery = true)
    Optional<JoinRequestEntity> findOneByIdAndClassId(@Param("id") UUID id,@Param("classId") UUID classId);
}
