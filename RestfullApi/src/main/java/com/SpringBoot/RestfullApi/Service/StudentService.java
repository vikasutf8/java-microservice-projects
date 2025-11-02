package com.SpringBoot.RestfullApi.Service;


import com.SpringBoot.RestfullApi.Dto.CreateStudentRequestDto;
import com.SpringBoot.RestfullApi.Dto.StudentDto;
import com.SpringBoot.RestfullApi.Entity.Student;
import com.SpringBoot.RestfullApi.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public interface StudentService {

    List<StudentDto> getAllStudent();

    StudentDto createStudent(CreateStudentRequestDto createStudentRequestDto);

    StudentDto getStudentById(Long studentId);

    void deleteStudentById(Long studentId);
    StudentDto updateStudent(CreateStudentRequestDto createStudentRequestDto,Long studentId);
    StudentDto updatePartialStudent(Map<String, Object> update,Long studentId);

}
