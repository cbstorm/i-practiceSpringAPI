package com.ipractice.springApi.Repositories;

import com.ipractice.springApi.Entities.JoinedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JoinedRepository extends JpaRepository<JoinedEntity, UUID> {
    @Query("SELECT j FROM joined j WHERE j.userId = :userid")
    List<JoinedEntity> findAllByUserId(@Param("userid") String userId);


    @Query(value = "SELECT * FROM joined WHERE class_id = :classId AND user_id = :userid", nativeQuery = true)
    Optional<JoinedEntity> findOneByClassIdAndUserId(@Param("classId") UUID classId, @Param("userid") String userId);

    @Query(value = "SELECT COUNT(*) FROM joined WHERE class_id = :classId", nativeQuery = true)
    int countJoinedByClassId(@Param("classId") UUID classId);
}
