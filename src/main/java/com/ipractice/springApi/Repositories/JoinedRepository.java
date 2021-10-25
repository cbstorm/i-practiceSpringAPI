package com.ipractice.springApi.Repositories;

import com.ipractice.springApi.Entities.JoinedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface JoinedRepository extends JpaRepository<JoinedEntity, UUID> {
    @Query("SELECT j FROM joined j WHERE j.userId = :userid")
    List<JoinedEntity> findAllByUserId(@Param("userid") String userId);
}
