package com.SpringBoot.RestfullApi;


import com.SpringBoot.RestfullApi.Entity.Patient;
import com.SpringBoot.RestfullApi.Repository.PatientRepository;
import com.SpringBoot.RestfullApi.Service.PatientService;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;


    @Test
    public void testPatientRepository(){
//        System.out.println(patientRepository.findAll());
//        Hibernate: select p1_0.id,p1_0.birth_date,p1_0.email,p1_0.gender,p1_0.name from patients p1_0
//        []
    }

    @Test
    public void testTransectionMethod(){
//        Patient patient =patientService.getPatientById(1L);
//
//        System.out.println(patient);
    }
}
