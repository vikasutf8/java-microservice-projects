package com.SpringBoot.RestfullApi.Service.Implementation;

import com.SpringBoot.RestfullApi.Dto.CreateStudentRequestDto;
import com.SpringBoot.RestfullApi.Dto.StudentDto;
import com.SpringBoot.RestfullApi.Entity.Student;
import com.SpringBoot.RestfullApi.Repository.StudentRepository;
import com.SpringBoot.RestfullApi.Service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    private StudentDto convertToDTO(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public List<StudentDto> getAllStudent() {
        List<Student>  students = studentRepository.findAll();

        return  students
                .stream()
                .map(s->convertToDTO(s))
                .toList();
    }

    @Override
    public StudentDto createStudent(CreateStudentRequestDto createStudentRequestDto) {
        Student newStudent =modelMapper.map(createStudentRequestDto, Student.class); //java class
//        createStudentRequestDto object ==> student entity object(dbStudnet)

        Student dbStudent = studentRepository.save(newStudent);

        return convertToDTO(dbStudent);
//        dbStudent student entity object --> studnetdto object
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student =this.studentRepository.findById(studentId).orElseThrow(()-> new IllegalArgumentException("Student with this id not exist"));

        return convertToDTO(student);
    }

    @Override
    public void deleteStudentById(Long studentId) {
         if(!studentRepository.existsById(studentId)){
             throw new IllegalArgumentException("studnet doesn't exist of this +id"+studentId);
         }
         this.studentRepository.deleteById(studentId);
    }

    @Override
    public StudentDto updateStudent(CreateStudentRequestDto createStudentRequestDto, Long studentId) {
        Student student =this.studentRepository.findById(studentId).orElseThrow(()-> new IllegalArgumentException("Student with this id not exist"));

        modelMapper.map(createStudentRequestDto,student);

        student =studentRepository.save(student);

        return convertToDTO(student);

    }

    @Override
    public StudentDto updatePartialStudent(Map<String, Object> update, Long studentId) {
        Student student =this.studentRepository.findById(studentId).orElseThrow(()-> new IllegalArgumentException("Student with this id not exist"));

        update.forEach((field,value)->{
            switch (field){
                case "name" : student.setName((String) value); break;
                case "email" : student.setEmail((String) value); break;
                default:
                    throw new IllegalArgumentException("field are wrong") ;
            }
        });
        Student updatedstudent =studentRepository.save(student);
        return convertToDTO(updatedstudent);
    }
}
