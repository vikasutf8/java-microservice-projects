package com.SpringBoot.RestfullApi.Repository;

import com.SpringBoot.RestfullApi.Dto.CountBloodTypeResponseEnitity;
import com.SpringBoot.RestfullApi.Entity.Enum.BloodType;
import com.SpringBoot.RestfullApi.Entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
//    jpa query methods  --https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    Patient findByName(String name);
    Patient findByBirthDate(LocalDate dob);
//    Patient findByBirthDateOrEmail(LocalDate dob,String email);
//    Optional<Patient> findByBirthDateOrEmail(LocalDate dob, String email);
    List<Patient> findByBirthDateOrEmail(LocalDate dob, String email);

// write custom jpa query --> instead of method





    @Query("SELECT p  FROM Patient p where p.bloodType= ?1")
    List<Patient> findByBloodType( @Param("bloodType") BloodType bloodType);

    @Query("SELECT p FROM Patient p WHERE p.birthDate > :birthDate")
    List<Patient> findByBirthDateAfter(@Param("birthDate") LocalDate birthDate);


    @Query("SELECT p.bloodType, COUNT(p) FROM Patient p GROUP BY p.bloodType")
    List<Object[]> countPatientsByBloodType();


//    how to write Native query or Pure query
    @Query(value = "select * from patients_table", nativeQuery = true)
    List<Patient> findAllPatient();

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name WHERE p.id = :id")
    int updateNameById(@Param("id") Long id, @Param("name") String name);


//    projection of Jpql
@Query("SELECT new com.SpringBoot.RestfullApi.Dto.CountBloodTypeResponseEnitity(p.bloodType, COUNT(p)) FROM Patient p GROUP BY p.bloodType")
List<CountBloodTypeResponseEnitity> countBloodType();

//pagenation -- 2 types

    @Query(value = "select * from patients_table", nativeQuery = true)
    Page<Patient> findAllPatientPage(Pageable pageable);

    @Query(value = "select * from patients_table", nativeQuery = true)
    Slice<Patient> findAllPatientSlice(Pageable pageable);
}
