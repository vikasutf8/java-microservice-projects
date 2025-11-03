package com.SpringBoot.RestfullApi;


import com.SpringBoot.RestfullApi.Dto.CountBloodTypeResponseEnitity;
import com.SpringBoot.RestfullApi.Entity.Enum.BloodType;
import com.SpringBoot.RestfullApi.Entity.Patient;
import com.SpringBoot.RestfullApi.Repository.PatientRepository;
import com.SpringBoot.RestfullApi.Service.PatientService;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

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
        System.out.println("fetch  patinet");
//        Patient patient=patientRepository.findByName("Sophia Johnson");
//        System.out.println(patient+"this is patinet");
//
//        List<Patient> patient1 = patientRepository.findByBirthDateOrEmail(
//                LocalDate.of(2002, 1, 18),
//                "sophia.johnson@example.com"
//        );
//        System.out.println(patient1+"patient by dob or  emails");

//        List<Patient> patient2 =patientRepository.findByBloodType(BloodType.A_POSITIVE);
//        System.out.println(patient2+"patient by bllod type of jpa query custom");
//
//
//        List<Patient> patient3 =patientRepository.findByBirthDateAfter(LocalDate.of(2002, 1, 18));
//        System.out.println(patient3+"patient by bllod type of jpa query custom");
//        List<Object[]> results = patientRepository.countPatientsByBloodType();
//        results.stream()
//                .map(row -> "BloodType: " + row[0] + ", Count: " + row[1])
//                .forEach(System.out::println);
//
//        List<Patient> patient4  = patientRepository.findAllPatient();
//        System.out.println(patient4+"native quert ");
//        List<Patient> patient4  = patientRepository.findAllPatientwithappointment();
//        System.out.println(patient4+"joins with N+1 reslove ");
//findAllPatientwithappointment
//        int updated = patientRepository.updateNameById(3L, "Updated Name");
//        System.out.println("Rows updated: " + updated);
//

//        List<CountBloodTypeResponseEnitity> countbloodtype =patientRepository.countBloodType();
//        System.out.println(countbloodtype);

//        Page<Patient> patientpage =patientRepository.findAllPatientPage(PageRequest.of(0,10, Sort.by("full_name").descending()));
//        System.out.println(patientpage+"patinet pageable");
//
//        Slice<Patient> patientslice =patientRepository.findAllPatientSlice(PageRequest.of(5,4, Sort.by("birth_date").descending()));
//        System.out.println(patientslice+"patient slice");
    }

    @Test
    public void testTransectionMethod(){
//        Patient patient =patientService.getPatientById(1L);
//
//        System.out.println(patient);
    }
}
