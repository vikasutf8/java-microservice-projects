package com.SpringBoot.RestfullApi.Service.Implementation;

import com.SpringBoot.RestfullApi.Dto.CreateStudentRequestDto;
import com.SpringBoot.RestfullApi.Dto.StudentDto;
import com.SpringBoot.RestfullApi.Entity.Student;
import com.SpringBoot.RestfullApi.Repository.StudentRepository;
import com.SpringBoot.RestfullApi.Service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CacheManager cacheManager;


    public static final String CACHE_ALL = "_cache_all_student";
    public static final String CACHE_ID = "_cache_student_by_id";
    public static final String CACHE_EMAIL = "_cache_student_by_email";
    public static final String CACHE_NAME_EMAIL = "_cache_student_by_name_email";

    private StudentDto convertToDTO(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    @Cacheable(cacheNames = CACHE_ALL ,key = "'all'")
    public List<StudentDto> getAllStudent() {
        List<Student>  students = studentRepository.findAll();

        List<StudentDto> dtos = students.stream()
                .map(this::convertToDTO)
                .toList();

        //  caches of them
//1. putitng  cacheName init
        var cachedId =cacheManager.getCache(CACHE_ID);
        var cachedEmail = cacheManager.getCache(CACHE_EMAIL);
        var cachedNameEmail =cacheManager.getCache(CACHE_NAME_EMAIL);
//her put key in it
        for (StudentDto dto : dtos) {
            cachedId.put(dto.getId(), dto);
            cachedEmail.put(dto.getEmail(), dto);
            cachedNameEmail.put(dto.getName() + "_" + dto.getEmail(), dto);
        }


        return  dtos;
    }


    //When a new student is created → remove the “all students” cache data.
    @Override
//    @CacheEvict(cacheNames = "_cache_all_student", key = "'all'") // this is single cacghe remove ...but havign mutliple
    @Caching(
            evict = {
                    @CacheEvict(cacheNames = CACHE_ALL, key = "'all'"),
//                    @CacheEvict(cacheNames = CACHE_ID, allEntries = true),
//                    @CacheEvict(cacheNames = CACHE_EMAIL, allEntries = true),
//                    @CacheEvict(cacheNames = CACHE_NAME_EMAIL, allEntries = true)
            },
            put = {
                    @CachePut(cacheNames = CACHE_ID, key = "#result.id"),
                    @CachePut(cacheNames = CACHE_EMAIL, key = "#result.email"),
                    @CachePut(cacheNames = CACHE_NAME_EMAIL, key = "#result.name + '_' + #result.email")
            }
    )

    public StudentDto createStudent(CreateStudentRequestDto createStudentRequestDto) {
        Student newStudent =modelMapper.map(createStudentRequestDto, Student.class); //java class
//        createStudentRequestDto object ==> student entity object(dbStudnet)

        Student dbStudent = studentRepository.save(newStudent);

        return convertToDTO(dbStudent);
//        dbStudent student entity object --> studnetdto object
    }

    @Override
    @Cacheable(cacheNames = "_cache_fetch_student_id", key = "'#studentId'")
    public StudentDto getStudentById(Long studentId) {
        Student student =this.studentRepository.findById(studentId).orElseThrow(()-> new IllegalArgumentException("Student with this id not exist"));

        return convertToDTO(student);
    }

    @Override
    public void deleteStudentById(Long studentId) {
         if(!studentRepository.existsById(studentId)){
             throw new IllegalArgumentException("student doesn't exist of this +id"+studentId);
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
