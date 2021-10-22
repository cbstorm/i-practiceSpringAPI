package com.ipractice.springApi.Repositories;

import com.ipractice.springApi.Entities.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassRepository extends JpaRepository<ClassEntity, UUID> {
}
