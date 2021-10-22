package com.ipractice.springApi.Services;

import com.ipractice.springApi.Entities.ClassEntity;
import com.ipractice.springApi.Repositories.ClassRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface ClassService {

    ClassEntity createClass(ClassEntity classEntity);

}
