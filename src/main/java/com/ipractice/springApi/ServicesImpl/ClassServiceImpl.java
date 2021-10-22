package com.ipractice.springApi.ServicesImpl;

import com.ipractice.springApi.Entities.ClassEntity;
import com.ipractice.springApi.Repositories.ClassRepository;
import com.ipractice.springApi.Services.ClassService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private ClassRepository classRepository;

    @Override
    public ClassEntity createClass(ClassEntity classEntity) {
        return classRepository.save(classEntity);
    }
}
