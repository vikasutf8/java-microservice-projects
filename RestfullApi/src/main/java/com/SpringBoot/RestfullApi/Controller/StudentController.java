package com.SpringBoot.RestfullApi.Controller;

import com.SpringBoot.RestfullApi.Dto.CreateStudentRequestDto;
import com.SpringBoot.RestfullApi.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.SpringBoot.RestfullApi.Dto.StudentDto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;


    @GetMapping("all")
    public ResponseEntity<List<StudentDto>> getAllStudent(){
//        return this.studentService.getAllStudent();
//        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudent());
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @GetMapping("{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long studentId){
        return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    @PostMapping("create")
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody CreateStudentRequestDto createStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(createStudentRequestDto));
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


    @PutMapping("{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@Valid @RequestBody CreateStudentRequestDto createStudentRequestDto, @PathVariable Long studentId){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.updateStudent(createStudentRequestDto,studentId));
    }

    @PatchMapping("{studentId}")
    public ResponseEntity<StudentDto> updatePartialStudent(@Valid @RequestBody Map<String , Object> update, @PathVariable Long studentId){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.updatePartialStudent(update,studentId));
    }
}

